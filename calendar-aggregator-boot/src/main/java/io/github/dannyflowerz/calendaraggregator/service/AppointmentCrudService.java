package io.github.dannyflowerz.calendaraggregator.service;

import java.time.LocalDate;
import java.util.List;

import io.github.dannyflowerz.calendaraggregator.model.Appointment;

public interface AppointmentCrudService {

    List<Appointment> getAppointments(LocalDate startDate, LocalDate endDate);

}
