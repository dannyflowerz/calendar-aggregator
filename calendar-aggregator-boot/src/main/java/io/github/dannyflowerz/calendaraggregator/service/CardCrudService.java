package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.model.CardResponse;

import java.util.List;
import java.util.Optional;

public interface CardCrudService {

    Optional<List<CardResponse.Card>> getCards(String customerId);

}
