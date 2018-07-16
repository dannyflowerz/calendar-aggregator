package io.github.dannyflowerz.calendaraggregator.controller;

import io.github.dannyflowerz.calendaraggregator.model.Card;
import io.github.dannyflowerz.calendaraggregator.service.CardCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
    public List<Card> getCards(@RequestParam String startDate, @RequestParam String endDate) {
    	LocalDate start = LocalDate.parse(startDate);
    	LocalDate end = LocalDate.parse(endDate);
        return Stream.concat(debitCardCrudService.getAppointments(start, end).stream(), creditCardCrudService.getAppointments(start, end).stream())
                .collect(Collectors.toList());
    }

}
