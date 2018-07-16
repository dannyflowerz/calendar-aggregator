package io.github.dannyflowerz.calendaraggregator.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreditCard {

    private String pan;
    private String cardHolderName;
    private String cardNumber;
    private Integer limit;

}
