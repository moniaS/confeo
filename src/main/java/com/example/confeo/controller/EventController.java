package com.example.confeo.controller;

import com.example.confeo.model.Event;
import com.example.confeo.model.EventType;
import com.example.confeo.service.CategoryService;
import com.example.confeo.service.EventService;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String saveEvent(@ModelAttribute @Valid Event event, BindingResult bindingResult,
    		RedirectAttributes redirectAttributes) {
    	
    	if (event.getStartDate() == null || event.getEndDate() == null){
    		redirectAttributes.addFlashAttribute("message", "Proszę podać datę rozpoczęcia i zakończenia");
    		redirectAttributes.addFlashAttribute("event", event);
    		return "redirect:/events/add";
    	} else if(event.getEndDate().compareTo(event.getStartDate()) < 0) {
    		redirectAttributes.addFlashAttribute("message", "Proszę podać datę rozpoczęcia starszą niż data zakończenia");
    		redirectAttributes.addFlashAttribute("event", event);
    		return "redirect:/events/add";
    	} else if (event.getName() == null || event.getName() == ""){
    		redirectAttributes.addFlashAttribute("message", "Proszę podać nazwę wydarzenia");
    		redirectAttributes.addFlashAttribute("event", event);
    		return "redirect:/events/add";
    	} 
    	LocalDate testDate = LocalDate.now();
    	System.out.println("daty: " + testDate + ", " + event.getStartDate());
    	Duration duration = Duration.between(testDate.atStartOfDay(), event.getStartDate().atStartOfDay());
    	//long daysBetween = DAYS.between(testDate, event.getStartDate());
    	long diff = Math.abs(duration.toDays());
    	System.out.println("roznica: " + diff);
    	if (diff > 180) {
    		redirectAttributes.addFlashAttribute("message", "Proszę podać datę wydarzenia maksymalnie do pół roku wprzód");
    		redirectAttributes.addFlashAttribute("event", event);
    		return "redirect:/events/add";
    	}
    	//prawdopodobnie nie bedziemy tego fragmentu uzywac
    	/*if (bindingResult.hasErrors()){
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
		    redirectAttributes.addFlashAttribute("event", event);
    		return "redirect:/events/add";
    	}*/
    	eventService.saveEvent(event);
        return "redirect:/events/" + event.getId();
    }
}
