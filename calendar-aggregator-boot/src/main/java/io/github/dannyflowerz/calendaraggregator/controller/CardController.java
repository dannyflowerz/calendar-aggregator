package io.github.dannyflowerz.calendaraggregator.controller;

import io.github.dannyflowerz.calendaraggregator.domain.Constants;
import io.github.dannyflowerz.calendaraggregator.model.Card;
import io.github.dannyflowerz.calendaraggregator.service.CardCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CardController {
	
    @Autowired
    CardCrudService debitCardCrudService;
    @Autowired
    CardCrudService creditCardCrudService;

    @GetMapping("/cards")
    public List<Card> getCards(@RequestHeader(name = Constants.CUSTOMER_ID_HEADER) String customerId) {
        return Stream.concat(debitCardCrudService.getCards(customerId).stream(), creditCardCrudService.getCards(customerId).stream())
                .collect(Collectors.toList());
    }

}
