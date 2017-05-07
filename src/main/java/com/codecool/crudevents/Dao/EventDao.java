package com.codecool.crudevents.Dao;

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

    public ArrayList<Event> convertToEvents() {
        ArrayList<Event> result = new ArrayList<>();
        try {
            ResultSet dbResult = database.executeSelectQuery("SELECT events.id, name, start_date, end_date, info, additional_info \n" +
                    "FROM events JOIN descriptions \n" +
                    "ON (events.description_id = descriptions.id);");
            while (dbResult.next()) {
                Event addMe = new Event(dbResult.getInt(1), dbResult.getString(2),
                        dbResult.getString(3), dbResult.getString(4),
                        new Description(dbResult.getString(5), dbResult.getString(6)));
                result.add(addMe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        EventDao testDao = new EventDao();
        System.out.println(testDao.convertToEvents());
    }
}
