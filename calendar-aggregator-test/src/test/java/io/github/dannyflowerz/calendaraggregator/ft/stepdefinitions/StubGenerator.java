package io.github.dannyflowerz.calendaraggregator.ft.stepdefinitions;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.model.CreditCard;
import io.github.dannyflowerz.calendaraggregator.model.DebitCard;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

final class StubGenerator {

    static final Gson gson = new GsonBuilder().create();

    private StubGenerator() {}

    static void genericStub(HttpMethod method, String backEnd, String resource, String customerId, Integer status, String responseMessage) {
        switch (method) {
            case GET:
                stubFor(get(urlMatching("/api/" + backEnd + '/' + resource))
                        .withHeader(Constants.CUSTOMER_ID_HEADER, equalTo(customerId))
                        .willReturn(aResponse()
                                .withStatus(status)
                                .withHeader("Content-Type", "application/json")
                                .withBody(responseMessage)));
            default:
                break;
        }
    }

    static void getDebitCardsStub(String customerId, int status, int count) {
        stubFor(get(urlMatching("/api/gDebitCards/debit-cards"))
                .withHeader(Constants.CUSTOMER_ID_HEADER, equalTo(customerId))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(generateDebitCards(count)))));
    }

    static void getCreditCardsStub(String customerId, int status, int count) {
        stubFor(get(urlMatching("/api/gCreditCards/credit-cards"))
                .withHeader(Constants.CUSTOMER_ID_HEADER, equalTo(customerId))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(StubGenerator.generateCreditCards(count)))));
    }

    private static List<DebitCard> generateDebitCards(int numberToGenerate) {
        List<DebitCard> debitCards = new ArrayList<>();
        IntStream.range(0, numberToGenerate).forEach(i -> debitCards.add(DebitCard.builder()
                .pan("pan" + i)
                .cardHolderName("John Doe")
                .cardNumber("001")
                .build()));
        return debitCards;
    }

    private static List<CreditCard> generateCreditCards(int numberToGenerate) {
        List<CreditCard> creditCards = new ArrayList<>();
        IntStream.range(0, numberToGenerate).forEach(i -> creditCards.add(CreditCard.builder()
                .pan("pan" + i)
                .cardHolderName("John Doe")
                .cardNumber("001")
                .limit(2500)
                .build()));
        return creditCards;
    }

}
