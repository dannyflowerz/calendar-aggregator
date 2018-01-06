package io.github.dannyflowerz.calendaraggregator.model;

import java.util.Date;

import lombok.Getter;

@Getter
public class OutlookAppointment {

    private Long id;
    private String title;
    private Date startTime;
    private Date endTime;

}
