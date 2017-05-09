package com.codecool.crudevents.controller;

import com.codecool.crudevents.Dao.CategoryDao;
import com.codecool.crudevents.Dao.EventDao;
import com.codecool.crudevents.model.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by mercutio on 07.05.17.
 */
public class EventController {
    private ArrayList<Event> listOfEvents;
    private EventDao defaultDao = new EventDao();
    private CategoryDao categoryDao = new CategoryDao();

    public EventController() {
        this.listOfEvents = defaultDao.convertAllToEvents();
    }

    public ArrayList<Event> getListOfEvents() {
        return listOfEvents;
    }

    public EventDao getDefaultDao() {
        return this.defaultDao;
    }

    public HashMap<String, String> getDictofEvents() {
        HashMap<String, String> result = new HashMap<>();
        for (Event event : getListOfEvents()) {
            String key = event.getId().toString();
            ArrayList<String> value = new ArrayList<>(Arrays.asList(event.getName(), event.getStartDate(), event.getEndDate(),
                    event.getDescription().getBasicInfo(), event.getDescription().getAdditionalInfo()));
            result.put(key, value.toString());


        }
        return result;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}
