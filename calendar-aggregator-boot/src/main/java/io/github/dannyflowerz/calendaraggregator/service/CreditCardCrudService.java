package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.configuration.CreditCardProperties;
import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.domain.UnresolvableException;
import io.github.dannyflowerz.calendaraggregator.model.CardResponse;
import io.github.dannyflowerz.calendaraggregator.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class CreditCardCrudService implements CardCrudService {

    @Autowired
    private CreditCardProperties creditCardProperties;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<List<CardResponse.Card>> getCards(String customerId) {
        URI url;
        try {
            url = new URI(creditCardProperties.getBaseUrl() + creditCardProperties.getGetCardsEndPoint());
        } catch (URISyntaxException e) {
            throw new UnresolvableException("Application misconfigured, unable to construct URL for creditr cards");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set(Constants.CUSTOMER_ID_HEADER, customerId);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CreditCard[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, CreditCard[].class);
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.empty();
        }
        return Optional.of(Stream.of(response.getBody())
                .map(CardTranslatorUtil::translate)
                .collect(Collectors.toList()));
    }

}
