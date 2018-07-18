package io.github.dannyflowerz.calendaraggregator.ft.stepdefinitions;

import cucumber.api.java8.En;
import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.model.CardResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class CommonStepDefs implements En {

    String customerId;
    Response response;

    public CommonStepDefs() {
        Before(() -> {
            configureFor(9081);
        });

        Given("^the customer \"([^\"]*)\"$", (String customerId) -> this.customerId = customerId);

        Given("^the (gDebitCards|gCreditCards) API responds with status (\\d{3}) for a (GET|POST|PUT|DELETE) request on the resource \"([^\"]*)\" for this customer$",
                (String backEnd, Integer status, HttpMethod method, String resource) -> StubGenerator.genericStub(method, backEnd, resource, customerId, status, null));

        Then("^the (gDebitCards|gCreditCards) API receives a (GET|POST|PUT|DELETE) request on the resource \"([^\"]*)\" for this customer$",
                (String backEnd, HttpMethod method, String resource) -> {
                    switch (method) {
                        case GET:
                            verify(getRequestedFor(urlEqualTo("/api/" + backEnd + '/' + resource))
                                    .withHeader(Constants.CUSTOMER_ID_HEADER, equalTo(customerId)));
                        default:
                            break;
                    }
                });

        Then("^I receive a response with status (\\d{3})$",
                (Integer status) -> assertEquals(status.intValue(), response.getStatusCode()));

        Then("^the response contains the error message \"([^\"]*)\"$", (String message) -> {
            CardResponse cardResponse = StubGenerator.gson.fromJson(response.getBody().print(), CardResponse.class);
            assertEquals(message, cardResponse.getErrorMessage());
        });
    }

}
