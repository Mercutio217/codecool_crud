package com.codecool.crudevents;
import com.codecool.crudevents.controller.WebController;

/**
 * Created by mercutio on 07.05.17.
 */
public class Main {
    static WebController defaultWeb = new WebController();

    public static void main(String[] args) {
        defaultWeb.showIndex();
        defaultWeb.showDetails();
        defaultWeb.showByCategory();
        defaultWeb.showAddForm();
        defaultWeb.goBack();
    }


}
