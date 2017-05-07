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
        ArrayList<Event> lista = kontrol.getListOfEvents();
        System.out.println(lista);
        Map<String, String> mapa = new HashMap<>();
        mapa.put("dupa", "jajco");
        mapa.put("oranrrr", "lolololol");
        ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        ModelAndView model = new ModelAndView(mapa, "hello");
        get("/", (request, response) -> "Hello, nygga!");
        get("/hello", (request, response) -> model, templateEngine);

    }


}
