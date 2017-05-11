package com.codecool.crudevents.controller;

import static spark.Spark.*;

import com.codecool.crudevents.model.Category;
import com.codecool.crudevents.model.Description;
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
    ThymeleafTemplateEngine thymeEngine = new ThymeleafTemplateEngine();
    HashMap<String, Object> routeMap = new HashMap<>();

    public ModelAndView createModelAndView(Object object, String viewName) {
        return new ModelAndView(object, viewName);
    }
    public void showIndex() {
        routeMap.put("events", eventList);
        routeMap.put("control", this);
        routeMap.put("category_list", categoryList);
        ModelAndView model = createModelAndView(routeMap, "index");

        get("/", (request, response) -> {
            return thymeEngine.render(model);
        });
    }

    public void goBack() {
        get("details/back", ((Request request, Response response) -> {
            response.redirect("/");
            return "";
        }));
    }

    public String getEventDetails(Integer id) {
        routeMap.put("event", eventController.getDefaultDao().getObjectById(id));
        routeMap.put("id", id);
        return thymeEngine.render(createModelAndView(routeMap, "details"));


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
        routeMap.put("events", resultList);

        return thymeEngine.render(createModelAndView(routeMap, "index"));
    }

    public void showByCategory() {
        get("/find/:name", (request, response) -> getByCategory(request.params(":name")));
    }

    public void showAddForm() {
        routeMap.put("categories", categoryList);
        get("/add_event", ((request, response) -> thymeEngine.render(createModelAndView(routeMap, "newEvent"))));
    }

    public void addEvent() {
        post("/add_event", (((request, response) -> {
            String name = request.queryParams("event_name");
            String categoryName = request.queryParams("category");
            Category category = Category.findByName(categoryName);
            Event newEvent = new Event(name, category);
            newEvent.setStartDate(request.queryParams("event_startdate"));
            if (!newEvent.setStartDate(request.queryParams("event_startdate"))) {
                routeMap.put("invalid_start_date", "Invalid format of start date!");
            }
            newEvent.setEndDate(request.queryParams("event_enddate"));
            if (!newEvent.setEndDate(request.queryParams("event_enddate"))) {
                routeMap.put("invalid_end_date", "Invalid format of end date!");
            }
            if (!newEvent.setStartDate(request.queryParams("event_startdate")) || !newEvent.setEndDate(request.queryParams("event_enddate"))) {
                return thymeEngine.render(createModelAndView(routeMap, "add_event"));
            }
            Description description =
                    new Description(request.queryParams("event_description"), request.queryParams("event_additional_info"));
            newEvent.setDescription(description);
            eventController.getDefaultDao().addEvent(newEvent);

            return newEvent.getName();
        })));

    }

    public static void main(String[] args) {

    }


}
