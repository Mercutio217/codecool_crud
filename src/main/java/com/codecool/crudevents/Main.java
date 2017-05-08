package com.codecool.crudevents;

import static spark.Spark.*;

import com.codecool.crudevents.controller.EventController;
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
    public static void main(String[] args) {
        EventController kontrol = new EventController();
        ArrayList<Event> eventList = kontrol.getListOfEvents();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("events", eventList);
        staticFileLocation("/css/");
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        ModelAndView model = new ModelAndView(resultMap, "hello");
        ModelAndView finalModel = model;
        get("/index", (request, response) -> templateEngine.render(finalModel));
        model = new ModelAndView(resultMap, "details");
        ModelAndView finalModel1 = model;
        get("/details", (request, response) -> templateEngine.render(finalModel1));


    }


}
