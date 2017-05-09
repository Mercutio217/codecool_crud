package com.codecool.crudevents.Dao;

import com.codecool.crudevents.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            ResultSet dbResult = database.executeSelectQuery("SELECT name FROM categories;");
            while (dbResult.next()) {
                Category category = new Category(dbResult.getString(1));
                result.add(category);
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQL exeption!!");
            return null;
        }
    }
}
