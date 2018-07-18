package io.github.dannyflowerz.calendaraggregator.service;


import io.github.dannyflowerz.calendaraggregator.model.CardResponse;
import io.github.dannyflowerz.calendaraggregator.model.CreditCard;
import io.github.dannyflowerz.calendaraggregator.model.DebitCard;

final class CardTranslatorUtil {

    private CardTranslatorUtil() {}

    static CardResponse.Card translate(DebitCard debitCard) {
        return CardResponse.Card.builder()
                .type(CardResponse.Card.Type.DEBIT)
                .pan(debitCard.getPan())
                .cardHolderName(debitCard.getCardHolderName())
                .cardNumber(debitCard.getCardNumber())
                .build();
    }

    static CardResponse.Card translate(CreditCard creditCard) {
        return CardResponse.Card.builder()
                .type(CardResponse.Card.Type.CREDIT)
                .pan(creditCard.getPan())
                .cardHolderName(creditCard.getCardHolderName())
                .cardNumber(creditCard.getCardNumber())
                .limit(creditCard.getLimit())
                .build();
    }

}
