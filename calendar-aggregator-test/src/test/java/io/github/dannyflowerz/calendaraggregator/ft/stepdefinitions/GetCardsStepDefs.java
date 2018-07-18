package io.github.dannyflowerz.calendaraggregator.ft.stepdefinitions;

import cucumber.api.java8.En;
import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.model.CardResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetCardsStepDefs implements En {

    private CommonStepDefs commonStepDefs;

	public GetCardsStepDefs(CommonStepDefs commonStepDefs) {
	    this.commonStepDefs = commonStepDefs;

        Given("^this customer has (\\d+) debit cards$", (Integer debitCount) -> {
            StubGenerator.getDebitCardsStub(this.commonStepDefs.customerId, 200, debitCount);
        });

        Given("^this customer has (\\d+) credit cards$", (Integer creditCount) -> {
            StubGenerator.getCreditCardsStub(this.commonStepDefs.customerId, 200, creditCount);
        });
		
		When("^I get the cards of this customer from our API$", () -> {
			String url = "http://localhost:8081/cards";
			commonStepDefs.response = given().header(Constants.CUSTOMER_ID_HEADER, commonStepDefs.customerId).when().get(url);
		});
		
		Then("^the response contains (\\d+) cards$", (Integer count) -> {
            CardResponse response = StubGenerator.gson.fromJson(commonStepDefs.response.getBody().print(), CardResponse.class);
            assertEquals(count.intValue(), response.getCards().size());
		});
	}

}
