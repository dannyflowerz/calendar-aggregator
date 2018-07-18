package io.github.dannyflowerz.calendaraggregator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CardResponse {

    @Getter
    @Builder
    public static class Card {

        public enum Type {
            DEBIT,
            CREDIT
        }

        private Type type;
        private String pan;
        private String cardHolderName;
        private String cardNumber;
        private Integer limit;

    }

    private List<Card> cards;
    private String errorMessage;

}
