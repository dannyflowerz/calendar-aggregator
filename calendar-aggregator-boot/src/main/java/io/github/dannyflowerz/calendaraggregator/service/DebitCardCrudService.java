package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.configuration.GoogleProperties;
import io.github.dannyflowerz.calendaraggregator.model.Card;
import io.github.dannyflowerz.calendaraggregator.model.CardTranslatorUtil;
import io.github.dannyflowerz.calendaraggregator.model.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class DebitCardCrudService implements CardCrudService {

    @Autowired
    private GoogleProperties googleProperties;

    @Override
    public List<Card> getAppointments(LocalDate startDate, LocalDate endDate) {
        String url = googleProperties.getBaseUrl() + googleProperties.getGetAppointmentsEndPoint() + "?startDate="  + startDate + "&endDate="  + endDate;
    	DebitCard[] debitCards = new RestTemplate().getForObject(url, DebitCard[].class);
        return Stream.of(debitCards)
                .map(CardTranslatorUtil::translate)
                .collect(Collectors.toList());
    }

}
