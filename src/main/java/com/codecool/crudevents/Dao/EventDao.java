package com.codecool.crudevents.Dao;

import com.codecool.crudevents.model.Category;
import com.codecool.crudevents.model.Description;
import com.codecool.crudevents.model.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mercutio on 07.05.17.
 */
public class EventDao {

    private JDBCHandler database;

    public EventDao() {
        this.database = JDBCHandler.getInstance();
    }

    public ArrayList<Event> convertAllToEvents() {
        ArrayList<Event> result = new ArrayList<>();
        try {
            ResultSet dbResult = database.executeSelectQuery("SELECT events.id, events.name, categories.name,\n" +
                    " start_date, end_date, info, additional_info FROM events\n" +
                    " JOIN descriptions ON (events.description_id = descriptions.id)\n" +
                    " JOIN categories ON (events.category_id = categories.id);");
            while (dbResult.next()) {
                Event addMe = new Event(dbResult.getInt(1), dbResult.getString(2),
                        new Category(dbResult.getString(3)), dbResult.getString(4),
                        dbResult.getString(5),
                        new Description(dbResult.getString(6), dbResult.getString(7)));
                result.add(addMe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Event getObjectById(Integer eventId) {
        try {
            ResultSet dbResult = database.executeSelectQuery(String.format("SELECT events.id, events.name, categories.name,\n" +
                    "\" +\n" +
                    "                    \" start_date, end_date, info, additional_info FROM events\\n\" +\n" +
                    "                    \" JOIN descriptions ON (events.description_id = descriptions.id)\\n\" +\n" +
                    "                    \" JOIN categories ON (events.category_id = categories.id) WHERE events.id = %d;", eventId));
            if (dbResult.next()) {
                return new Event(dbResult.getInt(1), dbResult.getString(2),
                        new Category(dbResult.getString(3)), dbResult.getString(4),
                        dbResult.getString(5),
                        new Description(dbResult.getString(6), dbResult.getString(7)));

            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println("SQL exeption in getObjectByID!!");
        }
        return null;
    }

}
