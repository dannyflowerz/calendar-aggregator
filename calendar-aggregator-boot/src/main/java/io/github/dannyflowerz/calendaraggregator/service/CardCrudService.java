package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.model.Card;

import java.util.List;

public interface CardCrudService {

    List<Card> getCards(String customerId);

}
