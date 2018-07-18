package io.github.dannyflowerz.calendaraggregator.controller;

import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.model.CardResponse;
import io.github.dannyflowerz.calendaraggregator.service.CardCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CardController {
	
    @Autowired
    CardCrudService debitCardCrudService;
    @Autowired
    CardCrudService creditCardCrudService;

    @GetMapping("/cards")
    public CardResponse getCards(@RequestHeader(name = Constants.CUSTOMER_ID_HEADER) String customerId) {
        Optional<List<CardResponse.Card>> debitCardsOpt = debitCardCrudService.getCards(customerId);
        if (!debitCardsOpt.isPresent()) {
            return new CardResponse(new ArrayList<>(), "We were not able to retrieve your cards at this time");
        }
        Optional<List<CardResponse.Card>> creditCardsOpt = creditCardCrudService.getCards(customerId);
        if (!creditCardsOpt.isPresent()) {
            return new CardResponse(debitCardsOpt.get(), "We were not able to retrieve your credit cards at this time");
        }
        return new CardResponse(Stream.concat(debitCardCrudService.getCards(customerId).get().stream(),
                creditCardCrudService.getCards(customerId).get().stream()).collect(Collectors.toList()), "");
    }

}
