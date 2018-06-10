package com.example.confeo.controller;

import com.example.confeo.form.EventSearchForm;
import com.example.confeo.model.Event;
import com.example.confeo.model.EventType;
import com.example.confeo.service.CategoryService;
import com.example.confeo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

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
        model.addAttribute("eventsByMonth", eventService.findEventsByMonth("%", "%", "%", LocalDate.MIN, LocalDate.MAX));
        addSearchValuesToModel(model);
        model.addAttribute("eventSearchForm", new EventSearchForm());
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
    	}
    	if (event.getName() == null || event.getName() == ""){
    		redirectAttributes.addFlashAttribute("message", "Proszę podać nazwę wydarzenia");
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

    @PostMapping("/events/search")
    private String searchEvents(EventSearchForm eventSearchForm, BindingResult bindingResult, Model model) {
        model.addAttribute("eventsByMonth", eventService.findEventsByMonth('%' + eventSearchForm.getName() + '%',
               '%' + eventSearchForm.getCity() + '%', '%' + eventSearchForm.getCategory() + '%', eventSearchForm.getStartDate(), eventSearchForm.getEndDate()));
        addSearchValuesToModel(model);
        return "events";
    }

    @GetMapping("/events/{id}")
    private String getEvent(@PathVariable("id") String id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        model.addAttribute("currentUser", currentUserName);
        model.addAttribute("event", eventService.findEvent(Long.valueOf(id)).get());
        return "event";
    }

    @GetMapping("/events/{id}/cancel")
    private String cancelEvent(@PathVariable("id") String id, Model model) {
        eventService.cancelEvent(Long.valueOf(id));
        model.addAttribute("event", eventService.findEvent(Long.valueOf(id)).get());
        return "event";
    }

    private void addSearchValuesToModel(Model model) {
        model.addAttribute("cities", eventService.findCities());
        model.addAttribute("categories", eventService.findCategories());
    }
}
