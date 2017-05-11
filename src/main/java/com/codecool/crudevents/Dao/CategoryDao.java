package com.codecool.crudevents.Dao;

import com.codecool.crudevents.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by mercutio on 09.05.17.
 */
public class CategoryDao {
    private JDBCHandler database;

    public CategoryDao() {
        this.database = JDBCHandler.getInstance();
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> result = new ArrayList<>();
        try {
            ResultSet dbResult = database.executeSelectQuery("SELECT id, name FROM categories;");
            while (dbResult.next()) {
                Category category = new Category(dbResult.getInt(1), dbResult.getString(2));
                result.add(category);
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQL exeption!!");
            return null;
        }
    }

    public Integer getIdByName(String name) {
        try {
            ResultSet dbResult = database.executeSelectQuery(String.format("SELECT id FROM categories WHERE (name = '%s');", name));
            while (dbResult.next()) {
                if (Objects.equals(name, dbResult.getString("name"))) {
                    return dbResult.getInt("id");
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL exeptioawdn!!");
            return null;
        }

        return null;
    }
}
