package com.example.confeo.controller;

import com.example.confeo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
@Controller
public class HomeController extends BasicController{

    private final EventService eventService;

    @Autowired
    public HomeController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("upcomingEvents", eventService.findUpcomingEventsForHomepage());
        model.addAttribute("finishedEvents", eventService.findFinishedEventsForHomepage());
        return "index";
    }
}
