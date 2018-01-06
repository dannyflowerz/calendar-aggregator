package io.github.dannyflowerz.calendaraggregator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("google")
@Getter
@Setter
public class GoogleProperties {

    private String baseUrl;
    private String getAppointmentsEndPoint;

}
