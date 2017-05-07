package com.codecool.crudevents.controller;

import com.codecool.crudevents.Dao.EventDao;
import com.codecool.crudevents.model.Event;
import com.codecool.crudevents.model.Description;

import java.util.ArrayList;

/**
 * Created by mercutio on 07.05.17.
 */
public class EventController {
    private ArrayList<Event> listOfEvents;
    private EventDao defaultDaou = new EventDao();

    public EventController() {
        this.listOfEvents = defaultDaou.convertToEvents();
    }

    public ArrayList<Event> getListOfEvents() {
        return listOfEvents;
    }
}
