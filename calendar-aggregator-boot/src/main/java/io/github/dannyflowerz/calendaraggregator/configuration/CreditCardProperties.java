package io.github.dannyflowerz.calendaraggregator.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("credit")
@Getter
@Setter
public class CreditCardProperties {

    private String baseUrl;
    private String getCardsEndPoint;

}
