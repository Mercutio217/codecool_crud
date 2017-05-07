package com.codecool.crudevents.model;

import java.util.Calendar;

/**
 * Created by mercutio on 07.05.17.
 */
public class Event {

    private String name;
    private Calendar startDate;
    private Calendar endDate;
    private Description description;

    public Event(String name) {
        this.name = name;
    }
    public Event(String name, Calendar startDate, Calendar endDate, Description description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
