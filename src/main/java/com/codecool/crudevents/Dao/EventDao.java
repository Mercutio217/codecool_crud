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
    private DescriptionDao descriptionDatabase = new DescriptionDao();

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
                        Category.findByName(dbResult.getString(3)), dbResult.getString(4),
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
            ResultSet dbResult = database.executeSelectQuery(String.format("SELECT events.id, events.name, categories.name, start_date, end_date, info, additional_info FROM events JOIN descriptions ON (events.description_id = descriptions.id) JOIN categories ON (events.category_id = categories.id) WHERE events.id = %d;", eventId));
            if (!dbResult.next()) {
                throw new SQLException();
            } else {
                return new Event(dbResult.getInt(1), dbResult.getString(2),
                        Category.findByName(dbResult.getString(3)),
                        dbResult.getString(4), dbResult.getString(5),
                        new Description(dbResult.getString(6), dbResult.getString(7)));

            }
        } catch (SQLException e) {
            System.out.println("SQL exception in getObjectByID!!");
        }
        return null;
    }

    public void addEvent(Event event) {
        descriptionDatabase.addDescription(event.getDescription());
        database.executeUpdateQuery(String.format("INSERT INTO events (name, description_id, start_date, end_date, category_id) VALUES ('%s', %d, '%s', '%s', %d);",
                event.getName(), descriptionDatabase.getLastRecordId(),
                event.getStartDate(), event.getEndDate(), event.getCategory().getId()));
    }

}
