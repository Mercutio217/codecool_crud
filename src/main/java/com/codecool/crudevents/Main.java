package com.codecool.crudevents;

import static spark.Spark.*;

import com.codecool.crudevents.controller.EventController;
import com.codecool.crudevents.controller.WebController;
import com.codecool.crudevents.model.Event;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mercutio on 07.05.17.
 */
public class Main {
    static WebController defaultWeb = new WebController();

    public static void main(String[] args) {
        defaultWeb.showIndex();
//        defaultWeb.showDetails();
        defaultWeb.goBack();
        get("/details/:name", (request, response) -> defaultWeb.showDetails(Integer.parseInt(request.params(":name"))));
    }


}
