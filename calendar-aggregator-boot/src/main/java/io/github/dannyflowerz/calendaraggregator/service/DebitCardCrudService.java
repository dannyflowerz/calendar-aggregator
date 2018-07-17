package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.configuration.DebitCardProperties;
import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.domain.UnresolvableException;
import io.github.dannyflowerz.calendaraggregator.model.Card;
import io.github.dannyflowerz.calendaraggregator.model.CardTranslatorUtil;
import io.github.dannyflowerz.calendaraggregator.model.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class DebitCardCrudService implements CardCrudService {

    @Autowired
    private DebitCardProperties debitCardProperties;

    @Override
    public List<Card> getCards(String customerId) {
        URI url;
        try {
            url = new URI(debitCardProperties.getBaseUrl() + debitCardProperties.getGetCardsEndPoint());
        } catch (URISyntaxException e) {
            throw new UnresolvableException("Application misconfigured, unable to construct URL for debit cards");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set(Constants.CUSTOMER_ID_HEADER, customerId);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        DebitCard[] debitCards = new RestTemplate().exchange(url, HttpMethod.GET, entity, DebitCard[].class).getBody();
        return Stream.of(debitCards)
                .map(CardTranslatorUtil::translate)
                .collect(Collectors.toList());
    }

}
