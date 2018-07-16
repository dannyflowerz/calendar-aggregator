package io.github.dannyflowerz.calendaraggregator.service;

import io.github.dannyflowerz.calendaraggregator.model.Card;

import java.time.LocalDate;
import java.util.List;

public interface CardCrudService {

    List<Card> getAppointments(LocalDate startDate, LocalDate endDate);

}
