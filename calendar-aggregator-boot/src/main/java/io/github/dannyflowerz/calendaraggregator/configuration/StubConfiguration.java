package io.github.dannyflowerz.calendaraggregator.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
@Profile("stubbed")
public class StubConfiguration {

    class StubRunner {

        WireMockServer wireMockServer = new WireMockServer(options().port(9081));

        void start() {
            wireMockServer.start();
            configureFor(9081);
            stubFor(get(urlMatching("/api/gDebitCards/debit-cards")).atPriority(99)
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBody("[]")));
            stubFor(get(urlMatching("/api/gCreditCards/credit-cards")).atPriority(99)
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
