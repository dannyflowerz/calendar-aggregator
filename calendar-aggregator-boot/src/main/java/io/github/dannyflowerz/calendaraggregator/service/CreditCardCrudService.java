package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.configuration.OutlookProperties;
import io.github.dannyflowerz.calendaraggregator.model.Card;
import io.github.dannyflowerz.calendaraggregator.model.CardTranslatorUtil;
import io.github.dannyflowerz.calendaraggregator.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class CreditCardCrudService implements CardCrudService {

    @Autowired
    private OutlookProperties outlookProperties;

    @Override
    public List<Card> getAppointments(LocalDate startDate, LocalDate endDate) {
    	String url = outlookProperties.getBaseUrl() + outlookProperties.getGetAppointmentsEndPoint() + "?startDate="  + startDate + "&endDate="  + endDate;
    	CreditCard[] creditCards = new RestTemplate().getForObject(url, CreditCard[].class);
        return Stream.of(creditCards)
                .map(CardTranslatorUtil::translate)
                .collect(Collectors.toList());
    }

}
