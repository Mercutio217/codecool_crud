package com.codecool.crudevents.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mercutio on 09.05.17.
 */
public class Category {
    private String name;
    private static List<Category> listOfCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
        listOfCategories.add(this);
    }

    public String getName() {
        return name;
    }

    public String getHttpName() {
        String result = this.getName().replaceAll(" {1,2}", "+");
        return result;
    }

    public static List<Category> getListOfCategories() {
        return listOfCategories;
    }
}
