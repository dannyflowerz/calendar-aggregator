package io.github.dannyflowerz.calendaraggregator.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dannyflowerz.calendaraggregator.model.Appointment;
import io.github.dannyflowerz.calendaraggregator.service.AppointmentCrudService;

@RestController
public class CalendarController {

    @Autowired
    AppointmentCrudService googleAppointmentCrudService;
    @Autowired
    AppointmentCrudService outlookAppointmentCrudService;

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() { // TODO startDate + endDate or today, etc
        return Stream.concat(googleAppointmentCrudService.getAppointments().stream(), outlookAppointmentCrudService.getAppointments().stream())
                .collect(Collectors.toList());
    }

}
