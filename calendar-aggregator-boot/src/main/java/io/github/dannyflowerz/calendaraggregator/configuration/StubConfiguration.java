package io.github.dannyflowerz.calendaraggregator.configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.tomakehurst.wiremock.WireMockServer;

@Configuration
@Profile("stubbed")
public class StubConfiguration {

    class StubRunner {

        WireMockServer wireMockServer = new WireMockServer(options().port(9081));

        void start() {
            wireMockServer.start();
            configureFor(9081);
            stubFor(get(urlMatching("/google/appointments.*")).atPriority(99)
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBody("[]")));
            stubFor(get(urlMatching("/outlook/appointments.*")).atPriority(99)
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBody("[]")));
        }

        void stop() {
            wireMockServer.stop();
        }
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public StubRunner stubRunner() {
        return new StubRunner();
    }

}
