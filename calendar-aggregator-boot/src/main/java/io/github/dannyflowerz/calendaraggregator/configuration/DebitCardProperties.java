package io.github.dannyflowerz.calendaraggregator.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("debit")
@Getter
@Setter
public class DebitCardProperties {

    private String baseUrl;
    private String getCardsEndPoint;

}
