package io.github.dannyflowerz.calendaraggregator.ft.stepdefinitions;

import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import io.github.dannyflowerz.calendaraggregator.model.Appointment;
import io.github.dannyflowerz.calendaraggregator.model.GoogleAppointment;
import io.github.dannyflowerz.calendaraggregator.model.OutlookAppointment;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class GetAppointmentsStepDefs implements En {

    private static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    private String startDate;
    private String endDate;
    private Response response;

    public GetAppointmentsStepDefs() {
        Before(() -> {
            configureFor("localhost", 9081);
            happyStubs();
        });

        Given("the start date: (.*) and the end date: (.*)", (String start, String end) -> {
            startDate = start;
            endDate = end;
        });

        When("I GET the appointments for these dates", () -> {
            String url = "http://localhost:8081/appointments?startDate=" + startDate + "&endDate=" + endDate;
            response = RestAssured.get(url);
        });

        Then("the (google|outlook) server receives a valid GET request for appointments", (provider) -> {
            verify(exactly(1), getRequestedFor(urlEqualTo("/"+provider+"/appointments?startDate=" + startDate + "&endDate=" + endDate)));
        });

        Then("I receive a (\\d{3}) response with (\\d+) appointments", (Integer status, Integer count) -> {
            assertEquals(status.intValue(), response.getStatusCode());
            List<Appointment> appointments = GSON.fromJson(response.getBody().print(), List.class);
            assertEquals(count.intValue(), appointments.size());
        });
    }

    private void happyStubs() {
        List<GoogleAppointment> googleAppointments = Arrays.asList(
                GoogleAppointment.builder().id(UUID.randomUUID().toString()).title("Google 1").start(new Date()).end(new Date()).visibility("PRIVATE").build(),
                GoogleAppointment.builder().id(UUID.randomUUID().toString()).title("Google 2").start(new Date()).end(new Date()).visibility("PUBLIC").build()
        );
        List<OutlookAppointment> outlookAppointments = Arrays.asList(
                OutlookAppointment.builder().id(1L).title("Outlook 1").startTime(new Date()).endTime(new Date()).build(),
                OutlookAppointment.builder().id(2L).title("Outlook 2").startTime(new Date()).endTime(new Date()).build()
        );

        stubFor(get(urlMatching("/google/appointments.*"))
                .withQueryParam("startDate", equalTo("2017-01-08"))
                .withQueryParam("endDate", equalTo("2017-01-12"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(GSON.toJson(googleAppointments))));

        stubFor(get(urlMatching("/outlook/appointments.*"))
                .withQueryParam("startDate", equalTo("2017-01-08"))
                .withQueryParam("endDate", equalTo("2017-01-12"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(GSON.toJson(outlookAppointments))));
    }
}