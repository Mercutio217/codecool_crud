package com.codecool.crudevents.controller;

import static spark.Spark.*;

import com.codecool.crudevents.controller.EventController;
import com.codecool.crudevents.model.Event;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by mercutio on 08.05.17.
 */
public class WebController {
    EventController deffaultController = new EventController();
    ArrayList<Event> eventList = deffaultController.getListOfEvents();


    public void showIndex() {
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        Map<String, Object> allEventsMap = new HashMap<>();
        allEventsMap.put("events", eventList);
        ModelAndView model = new ModelAndView(allEventsMap, "index");

        get("/", (request, response) -> templateEngine.render(model));
    }

    public void showDetails(Integer id) {
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(id.toString(), deffaultController.getDefaultDao().getObjectById(id));
        ModelAndView model = new ModelAndView(resultMap, "description");
        get("/description/:id", (request, response) -> templateEngine.render(model));


    }


}
