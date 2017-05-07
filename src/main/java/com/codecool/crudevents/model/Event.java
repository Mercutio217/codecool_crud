package com.codecool.crudevents.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mercutio on 07.05.17.
 */
public class Event {

    private String name;
    private String startDate;
    private String endDate;
    private Description description;

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, String startDate, String endDate, Description description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

}
