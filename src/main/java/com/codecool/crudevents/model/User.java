package com.codecool.crudevents.model;

import java.util.ArrayList;

/**
 * Created by mercutio on 07.05.17.
 */
public class User {
    private String name;
    private String login;
    private String password;
    ArrayList<Event> eventList = new ArrayList<>();

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
