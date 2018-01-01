package io.github.dannyflowerz.calendaraggregator.model;


public final class AppointmentTranslatorUtil {

    private AppointmentTranslatorUtil() {}

    public static Appointment translate(GoogleAppointment googleAppointment) {
        return Appointment.builder()
                .id(googleAppointment.getId())
                .source(Appointment.Source.GOOGLE)
                .title(googleAppointment.getTitle())
                .startDate(googleAppointment.getStart())
                .endDate(googleAppointment.getEnd())
                .visibility(Appointment.Visibility.valueOf(googleAppointment.getVisibility()))
                .build();
    }

    public static Appointment translate(OutlookAppointment outlookAppointment) {
        return Appointment.builder()
                .id(outlookAppointment.getId().toString())
                .source(Appointment.Source.OUTLOOK)
                .title(outlookAppointment.getTitle())
                .startDate(outlookAppointment.getStartTime())
                .endDate(outlookAppointment.getEndTime())
                .visibility(Appointment.Visibility.PRIVATE)
                .build();
    }

}
