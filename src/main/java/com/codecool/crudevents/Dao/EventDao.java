package com.codecool.crudevents.Dao;

import com.codecool.crudevents.model.Event;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by mercutio on 07.05.17.
 */
public class EventDao {

    private JDBCHandler database;

    public EventDao() {
        this.database = JDBCHandler.getInstance();
    }

    public ArrayList<Event> convertToEvents() {
        ArrayList<Event> result = new ArrayList<>();
        try {
            ResultSet dbResult = database.executeSelectQuerfy(String query);
        }
    }
}
