package com.example.confeo.controller;

import com.example.confeo.model.Event;
import com.example.confeo.model.EventType;
import com.example.confeo.service.CategoryService;
import com.example.confeo.service.EventService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    	if (!model.containsAttribute("event")){
    		model.addAttribute("event", new Event());
    	}
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("categories", categoryService.findAll());
        return "add-event";
    }
    
    @PostMapping("/events/add/save")
    public String saveEvent(@Validated Event event, BindingResult bindingResult,
    		RedirectAttributes redirectAttributes) {
    	if (bindingResult.hasErrors()){
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
		    redirectAttributes.addFlashAttribute("event", event);
    		return "redirect:/events/add";
    	}
    	eventService.saveEvent(event);
        return "redirect:/events/" + event.getId();
    }
}
