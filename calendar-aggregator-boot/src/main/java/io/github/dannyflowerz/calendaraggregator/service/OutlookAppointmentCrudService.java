package io.github.dannyflowerz.calendaraggregator.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.dannyflowerz.calendaraggregator.configuration.OutlookProperties;
import io.github.dannyflowerz.calendaraggregator.model.Appointment;
import io.github.dannyflowerz.calendaraggregator.model.AppointmentTranslatorUtil;
import io.github.dannyflowerz.calendaraggregator.model.OutlookAppointment;

@Service
class OutlookAppointmentCrudService implements AppointmentCrudService {

    @Autowired
    private OutlookProperties outlookProperties;

    @Override
    public List<Appointment> getAppointments(LocalDate startDate, LocalDate endDate) {
    	String url = outlookProperties.getBaseUrl() + outlookProperties.getGetAppointmentsEndPoint() + "?startDate="  + startDate + "&endDate="  + endDate;
    	OutlookAppointment[] outlookAppointments = new RestTemplate().getForObject(url, OutlookAppointment[].class);
        return Stream.of(outlookAppointments)
                .map(AppointmentTranslatorUtil::translate)
                .collect(Collectors.toList());
    }

}
