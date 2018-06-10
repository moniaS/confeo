package com.example.confeo.controller;

import com.example.confeo.form.EventSearchForm;
import com.example.confeo.model.Event;
import com.example.confeo.model.EventType;
import com.example.confeo.service.CategoryService;
import com.example.confeo.service.EventService;
import com.example.confeo.service.UserService;

import java.time.Duration;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by mstobieniecka on 2018-05-29.
 */
@Controller
public class EventController {
    private final EventService eventService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, CategoryService categoryService, UserService userService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.userService = userService;
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
    	if (!isFormValidated(event, redirectAttributes)){
    		return "redirect:/events/add";
    	}
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	event.setOrganiser(userService.findByUsername(currentPrincipalName));
    	eventService.saveEvent(event);
        return "redirect:/events/" + event.getId();
    }
    
    @RequestMapping("/events/{id}/edit")
    public String editEvent(Model model, @PathVariable("id") long eventId) {
    	//jesli nie ma takiego wydarzenia -> przejdz do listy wydarzen
    	if (eventService.findById(eventId) == null){
    		return "redirect:/events";
    	}
    	if (!model.containsAttribute("event")){
    		model.addAttribute("event", eventService.findById(eventId));
    	}
    	
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("categories", categoryService.findAll());
        return "edit-event";
    }
    
    @PostMapping("/events/edit/save")
    public String saveEventEdit(@ModelAttribute @Valid Event event, BindingResult bindingResult,
    		RedirectAttributes redirectAttributes) {
    	if (!isFormValidated(event, redirectAttributes)){
    		return "redirect:/events/" + event.getId() + "/edit";
    	}   	
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
    
    private boolean isFormValidated(Event event, RedirectAttributes redirectAttributes){
    	LocalDate testDate = LocalDate.now();
    	if (event.getStartDate() == null || event.getEndDate() == null){
    		redirectAttributes.addFlashAttribute("message", "Proszę podać datę rozpoczęcia i zakończenia");
    		redirectAttributes.addFlashAttribute("event", event);
    		return false;
    	} else if (!event.getStartDate().isAfter(testDate)){
    		redirectAttributes.addFlashAttribute("message", "Proszę podać przyszłe daty");
    		redirectAttributes.addFlashAttribute("event", event);
    		return false;
    	} else if(event.getEndDate().compareTo(event.getStartDate()) < 0) {
    		redirectAttributes.addFlashAttribute("message", "Proszę podać datę rozpoczęcia starszą niż data zakończenia");
    		redirectAttributes.addFlashAttribute("event", event);
    		return false;
    	} else if (event.getName() == null || event.getName() == ""){
    		redirectAttributes.addFlashAttribute("message", "Proszę podać nazwę wydarzenia");
    		redirectAttributes.addFlashAttribute("event", event);
    		return false;
    	} 
    	Duration duration = Duration.between(testDate.atStartOfDay(), event.getStartDate().atStartOfDay());
    	long diff = Math.abs(duration.toDays());
    	if (diff > 180) {
    		redirectAttributes.addFlashAttribute("message", "Proszę podać datę wydarzenia maksymalnie do pół roku wprzód");
    		redirectAttributes.addFlashAttribute("event", event);
    		return false;
    	}
    	return true;
    }
}
