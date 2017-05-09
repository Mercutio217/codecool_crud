package com.codecool.crudevents.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mercutio on 07.05.17.
 */
public class Event {

    private Integer id;
    private String name;
    private Category category;
    private String startDate;
    private String endDate;
    private Description description;

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, Category category, String startDate, String endDate, Description description) {
        this.name = name;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Event(Integer id, String name, Category category, String startDate, String endDate, Description description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%d %s start: %s end: %s \n %s \n \n", this.id, this.name, this.startDate, this.endDate,
                this.description);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }


}
