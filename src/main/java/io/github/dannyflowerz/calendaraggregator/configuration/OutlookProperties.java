package io.github.dannyflowerz.calendaraggregator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("outlook")
@Getter
@Setter
public class OutlookProperties {

    private String baseUrl;
    private String getAppointmentsEndPoint;

}
