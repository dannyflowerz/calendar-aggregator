package io.github.dannyflowerz.calendaraggregator.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Appointment {

    public enum Visibility {
        PRIVATE,
        PUBLIC
    }

    public enum Source {
        GOOGLE,
        OUTLOOK
    }

    private String id;
    private Source source;
    private String title;
    private Date startDate;
    private Date endDate;
    private Visibility visibility;
//    private List<Attentdee> attendees = new ArrayList<>() //etc...

}
