package io.github.dannyflowerz.calendaraggregator.service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Appointment> getAppointments() {
        List<OutlookAppointment> outlookAppointments = new RestTemplate().getForObject(outlookProperties.getBaseUrl() + outlookProperties.getGetAppointmentsEndPoint(), List.class);
        return outlookAppointments.stream()
                .map(AppointmentTranslatorUtil::translate)
                .collect(Collectors.toList());
    }

}
