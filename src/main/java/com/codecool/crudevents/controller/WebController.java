package com.codecool.crudevents.controller;

import static spark.Spark.*;

import com.codecool.crudevents.model.Category;
import com.codecool.crudevents.model.Event;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;


/**
 * Created by mercutio on 08.05.17.
 */
public class WebController {
    EventController eventController = new EventController();
    ArrayList<Event> eventList = eventController.getListOfEvents();
    ArrayList<Category> categoryList = eventController.getCategoryDao().getCategories();


    public void showIndex() {
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        Map<String, Object> allEventsMap = new HashMap<>();
        allEventsMap.put("events", eventList);
        allEventsMap.put("control", this);
        allEventsMap.put("category_list", categoryList);
        ModelAndView model = new ModelAndView(allEventsMap, "index");

        get("/", (request, response) -> {
            return templateEngine.render(model);
        });
    }

    public void goBack() {
        get("details/back", ((Request request, Response response) -> {
            response.redirect("/");
            return "";
        }));
    }

    public String getEventDetails(Integer id) {
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("event", eventController.getDefaultDao().getObjectById(id));
        resultMap.put("id", id);
        ModelAndView model = new ModelAndView(resultMap, "details");
        return templateEngine.render(model);


    }

    public void showDetails() {
        get("/details/:name", (request, response) ->
                getEventDetails(Integer.parseInt(request.params(":name"))));

    }

    public String getByCategory(String name) {
        List<Event> resultList = new ArrayList<>();
        Iterator<Event> iterator = this.eventList.iterator();
        while (iterator.hasNext()) {
            Event itrNext = iterator.next();

            if ((Objects.equals(itrNext.getCategory().getName(), name)) || (Objects.equals(itrNext.getCategory().getHttpName(), name))) {
                resultList.add(itrNext);

            }


        }
        HashMap<String, List<Event>> resultMap = new HashMap<>();
        resultMap.put("events", resultList);
        ModelAndView model = new ModelAndView(resultMap, "index");
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        return templateEngine.render(model);
    }

    public void showByCategory() {
        get("/find/:name", (request, response) -> getByCategory(request.params(":name")));
    }

    public void showAddForm() {
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        HashMap<String, Object> defaultMap = new HashMap<>();
        ModelAndView model = new ModelAndView(defaultMap, "newEvent");
        get("/add_event", ((request, response) -> templateEngine.render(model)));
    }

    public void addEvent() {
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        HashMap<String, Object> defaultMap = new HashMap<>();
        ModelAndView model = new ModelAndView(defaultMap, "newEvent");
        post("/add_event", (((request, response) -> {
            String name = request.queryParams("event_name");
            String description = request.queryParams("event_description");
            return name + description;
        })));

    }


}
