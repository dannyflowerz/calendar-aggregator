package io.github.dannyflowerz.calendaraggregator.model;


public final class CardTranslatorUtil {

    private CardTranslatorUtil() {}

    public static Card translate(DebitCard debitCard) {
        return Card.builder()
                .type(Card.Type.DEBIT)
                .pan(debitCard.getPan())
                .cardHolderName(debitCard.getCardHolderName())
                .cardNumber(debitCard.getCardNumber())
                .build();
    }

    public static Card translate(CreditCard creditCard) {
        return Card.builder()
                .type(Card.Type.CREDIT)
                .pan(creditCard.getPan())
                .cardHolderName(creditCard.getCardHolderName())
                .cardNumber(creditCard.getCardNumber())
                .limit(creditCard.getLimit())
                .build();
    }

}
