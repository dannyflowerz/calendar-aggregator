package io.github.dannyflowerz.calendaraggregator.model;

import java.util.Date;

import lombok.Getter;

@Getter
public class GoogleAppointment {

    private String id;
    private String title;
    private Date start;
    private Date end;
    private String visibility;

}
