package com.example.confeo.controller;

import com.example.confeo.model.Event;
import com.example.confeo.model.Prelection;
import com.example.confeo.service.EventService;
import com.example.confeo.service.PrelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by mstobieniecka on 2018-06-11.
 */
@Controller
public class PrelectionController {
    private final PrelectionService prelectionService;
    private final EventService eventService;

    @Autowired
    public PrelectionController(PrelectionService prelectionService, EventService eventService) {
        this.prelectionService = prelectionService;
        this.eventService = eventService;
    }

    @RequestMapping("/events/{id}/makePrelection")
    public String createPrelection(Model model, @PathVariable("id") long eventId) {
        Event event = eventService.findEvent(eventId);
        Prelection prelection = new Prelection();
        prelection.setEvent(event);
        model.addAttribute("prelection", prelection);
        return "add-prelection";
    }

    @PostMapping("/prelections/add/save")
    public String savePrelection(@ModelAttribute @Valid Prelection prelection, Model model, RedirectAttributes redirectAttributes) {
        if(!isFormValid(prelection, redirectAttributes)) {
            return "redirect:/events/" + prelection.getEvent().getId() + "/makePrelection";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Prelection savedPrelection = prelectionService.addPrelection(prelection, prelection.getEvent().getId(), currentPrincipalName);
        return "redirect:/prelections/" + savedPrelection.getId();
    }

    @GetMapping("/prelections/{id}")
    public String getPrelection(@PathVariable("id") long id, Model model) {
        Prelection prelection = prelectionService.findPrelection(id);
        model.addAttribute("prelection", prelection);
        return "prelection";
    }

    private boolean isFormValid(Prelection prelection, RedirectAttributes redirectAttributes) {
        boolean isValid = true;
        if (prelection.getName() == null || prelection.getName().equals("")) {
            redirectAttributes.addFlashAttribute("nameError", "Nazwa prelekcji musi być podana");
            isValid = false;
        }
        if (prelection.getDescription() == null || prelection.getDescription().equals("")) {
            redirectAttributes.addFlashAttribute("descriptionError", "Opis prelekcji musi być podany");
            isValid = false;
        }
    return isValid;
    }
}
