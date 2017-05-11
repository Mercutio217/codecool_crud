package com.codecool.crudevents.Dao;

import com.codecool.crudevents.model.Description;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by mercutio on 11.05.17.
 */
public class DescriptionDao {
    private JDBCHandler database;

    public DescriptionDao() {
        this.database = JDBCHandler.getInstance();
    }

    public void addDescription(Description description) {
        database.executeUpdateQuery(String.format("INSERT INTO descriptions (info, additional_info) VALUES ('%s', '%s');",
                description.getBasicInfo(), description.getAdditionalInfo()));

    }


    public Integer getLastRecordId() {
        try {
            ResultSet dbResult =
                    database.executeSelectQuery("SELECT max(id) FROM descriptions;");
            while (dbResult.next()) {
                return dbResult.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("SQL exeption in get last record!!");
            return null;
        }

        return null;
    }
}
