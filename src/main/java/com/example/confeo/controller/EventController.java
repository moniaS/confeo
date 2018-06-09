package com.example.confeo.controller;

import com.example.confeo.model.Event;
import com.example.confeo.model.EventType;
import com.example.confeo.service.CategoryService;
import com.example.confeo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mstobieniecka on 2018-05-29.
 */
@Controller
public class EventController {
    private final EventService eventService;
    private final CategoryService categoryService;

    @Autowired
    public EventController(EventService eventService, CategoryService categoryService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/events")
    private String events(Model model) {
        model.addAttribute("eventsByMonth", eventService.findEventsByMonth());
        return "events";
    }
    
    @RequestMapping("/events/add")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("categories", categoryService.findAll());
        return "add-event";
    }
    
    @PostMapping("/events/add/save")
    public String saveEvent(Event event, Model model) {
    	//eventService.saveEvent(event);
        return "add-event";
    }
}
