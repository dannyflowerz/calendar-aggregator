package io.github.dannyflowerz.calendaraggregator.ft.stepdefinitions;

import cucumber.api.java8.En;

public class GetAppointmentsStepDefs implements En {

	private String startDate;
	private String endDate;
	
	public GetAppointmentsStepDefs() {
		Given("the start date: (.*) and the end date: (.*)", (String start, String end) -> {
			startDate = start;
			endDate = end;
		});
		
		When("I GET the appointments for these dates", () -> {
			// TODO make request and store response
		});
		
		Then("the [Google|Outlook] server receives a valid GET request for appointments", (provider) -> {
			// TODO wiremock verification
		});
		
		Then("I receive a (\\d{3}) response with (\\d+) appointments", (status, count) -> {
			// TODO validate stored response
		});
	}
	
}
