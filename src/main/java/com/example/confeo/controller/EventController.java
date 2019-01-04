package com.example.confeo.controller;

import com.example.confeo.exception.CannotSignUpOnCanceledEvent;
import com.example.confeo.exception.ParticipantsLimitReached;
import com.example.confeo.exception.XSSConstraintException;
import com.example.confeo.form.EventSearchForm;
import com.example.confeo.model.City;
import com.example.confeo.model.Event;
import com.example.confeo.model.EventType;
import com.example.confeo.model.Role;
import com.example.confeo.model.User;
import com.example.confeo.pdf.PdfGenerator;
import com.example.confeo.service.CategoryService;
import com.example.confeo.service.CityService;
import com.example.confeo.service.EventService;
import com.example.confeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by mstobieniecka on 2018-05-29.
 */
@Controller
public class EventController extends BasicController {
    private EventService eventService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CityService cityService;

    @Autowired
    public EventController(EventService eventService, CategoryService categoryService, UserService userService, CityService cityService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.cityService = cityService;
    }

    @RequestMapping("/events")
    private String events(Model model) {
        model.addAttribute("eventsByMonth", eventService.findEventsByMonth("%", "%", "%", LocalDate.MIN, LocalDate.MAX));
        addSearchValuesToModel(model);
        model.addAttribute("eventSearchForm", new EventSearchForm());
        return "events";
    }

    @RequestMapping("/my/events")
    private String myEvents(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        if(user.getRole().equals(Role.ROLE_PARTICIPANT)) {
            model.addAttribute("events", user.getAttendingEvents().stream().sorted(Comparator.comparing(Event::getStartDate)).collect(Collectors.toList()));
        } else if(user.getRole().equals(Role.ROLE_ORGANIZER)) {
            model.addAttribute("events", user.getOrganizedEvents().stream().sorted(Comparator.comparing(Event::getStartDate)).collect(Collectors.toList()));
        }
        return "my-events";
    }

    @RequestMapping("/events/add")
    public String addEvent(Model model) {
    	if (!model.containsAttribute("event")){
    		model.addAttribute("event", new Event());
    	}
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("categories", categoryService.findAll());
        /*for (String city: cityService.findAllCities()){
        	System.out.println(city);
        }*/
        model.addAttribute("cities", cityService.findAllCities());
        return "add-event";
    }

    @PostMapping("/events/add/save")
    public String saveEvent(@ModelAttribute @Valid Event event, BindingResult bindingResult,
    		RedirectAttributes redirectAttributes){
    	if (!isFormValidated(event, redirectAttributes)){
    		return "redirect:/events/add";
    	}
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	event.setOrganiser(userService.findByUsername(currentPrincipalName));
        try {
            eventService.saveEvent(event);
        } catch (XSSConstraintException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorRegisterMessage", "Formularz zawiera niedozwolone znaki");
            return "redirect:/events/add";
        }
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
    
    @PostMapping("/events/edit/{id}/save")
    public String saveEventEdit(@ModelAttribute @Valid Event event, @PathVariable("id") long eventId, BindingResult bindingResult,
    		RedirectAttributes redirectAttributes) {
    	if (!isFormValidated(event, redirectAttributes)){
    		return "redirect:/events/" + event.getId() + "/edit";
    	}  
    	event.setId(eventId);
    	eventService.updateEvent(event);
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
    	if (eventService.findById(Long.valueOf(id)) == null){
    		return "redirect:/events";
    	}
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        model.addAttribute("isSignedUpForEventAsParticipant", eventService.isUserSignedUpOnEventAsParticipant(currentUserName, Long.valueOf(id)));
        model.addAttribute("isSignedUpForEventAsPrelegent", eventService.isUserSignedUpOnEventAsPrelegent(currentUserName, Long.valueOf(id)));
        model.addAttribute("event", eventService.findById(Long.valueOf(id)));
        return "event";
    }

    @GetMapping("/events/{id}/cancel")
    private String cancelEvent(@PathVariable("id") String id, Model model) {
    	if (eventService.findById(Long.valueOf(id)) == null){
    		return "redirect:/events";
    	}
        eventService.cancelEvent(Long.valueOf(id));
        model.addAttribute("event", eventService.findById(Long.valueOf(id)));
        return "event";
    }

    @GetMapping("/events/{id}/signUp")
    private String signUpOnEvent(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            eventService.signUpOnEventAsParticipant(authentication.getName(), Long.valueOf(id));
            model.addAttribute("event", eventService.findEvent(Long.valueOf(id)));
            redirectAttributes.addFlashAttribute("successMessage", "Zostałeś zapisany na wydarzenie");
        } catch (ParticipantsLimitReached participantsLimitReached) {
            participantsLimitReached.printStackTrace();
            redirectAttributes.addFlashAttribute("failureMessage", "Limit miejsc na wydarzenie został już osiągnięty");
        } catch (CannotSignUpOnCanceledEvent cannotSignUpOnCanceledEvent) {
            cannotSignUpOnCanceledEvent.printStackTrace();
            redirectAttributes.addFlashAttribute("failureMessage", "Nie można zapisać się na anulowaną lub zakończoną konferencję");
        }
        return "redirect:/events/" + id;
    }

    @GetMapping("/events/{id}/signOff")
    private String signOffFromEvent(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        eventService.signOffFromEventAsParticipant(authentication.getName(), Long.valueOf(id));
        model.addAttribute("event", eventService.findEvent(Long.valueOf(id)));
        redirectAttributes.addFlashAttribute("successMessage", "Zostałeś wypisany z wydarzenia");
        return "redirect:/events/" + id;
    }

    @RequestMapping(value = "/events/{id}/pdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatePdf(@PathVariable("id") long id,  HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        Event event = eventService.findEvent(id);
        response.setContentType("application/pdf");
        ByteArrayInputStream bis = PdfGenerator.generateInvoice(event, user, "2018/06/" + eventService.getInvoiceNumber().toString());
        eventService.setInvoiceNumber(eventService.getInvoiceNumber() + 1);
        org.apache.commons.io.IOUtils.copy(bis, response.getOutputStream());
        response.flushBuffer();
    }

    private void addSearchValuesToModel(Model model) {
        model.addAttribute("cities", eventService.findCities());
        model.addAttribute("categories", eventService.findCategories());
    }

    private boolean isFormValidated(Event event, RedirectAttributes redirectAttributes){
    	LocalDate testDate = LocalDate.now();
    	boolean error = false;
    	try {
	    	
	    	if (event.getStartDate() == null || event.getEndDate() == null){
	    		//redirectAttributes.addFlashAttribute("message", "Proszę podać datę rozpoczęcia i zakończenia");
	    		//redirectAttributes.addFlashAttribute("event", event);
	    		error = true;
	    	} else if (!event.getStartDate().isAfter(testDate)){
	    		/*redirectAttributes.addFlashAttribute("message", "Proszę podać przyszłe daty");
	    		redirectAttributes.addFlashAttribute("event", event);
	    		return false;*/
	    		error = true;
	    	} else if(event.getEndDate().compareTo(event.getStartDate()) < 0) {
	    		/*redirectAttributes.addFlashAttribute("message", "Proszę podać datę rozpoczęcia starszą niż data zakończenia");
	    		redirectAttributes.addFlashAttribute("event", event);
	    		return false;*/
	    		error = true;
	    	} else if (event.getName() == null || event.getName() == ""){
	    		/*redirectAttributes.addFlashAttribute("message", "Proszę podać nazwę wydarzenia");
	    		redirectAttributes.addFlashAttribute("event", event);
	    		return false;*/
	    		error = true;
	    	} else if (event.getDescription().length() > 254){
	    		/*redirectAttributes.addFlashAttribute("message", "Proszę podać opis wydarzenia do 250 znaków");
	    		redirectAttributes.addFlashAttribute("event", event);*/
	    		error = true;
	    	} else if (event.getAddress().getCityName() == null || event.getAddress().getCityName() == ""){
	    		error = true;
	    	} else if (event.getAddress().getStreetName() == null || event.getAddress().getStreetName() == ""){
	    		error = true;
	    	} else if (event.getAddress().getStreetNumber() == null || event.getAddress().getStreetNumber() == ""){
	    		error = true;
	    	} else if (event.getMaxParticipants() < 1){
	    		error = true;
	    	}
    	
    	
			Duration duration = Duration.between(testDate.atStartOfDay(), event.getStartDate().atStartOfDay());
			long diff = Math.abs(duration.toDays());
			if (diff > 180) {
				/*redirectAttributes.addFlashAttribute("message", "Proszę podać datę wydarzenia maksymalnie do pół roku wprzód");
				redirectAttributes.addFlashAttribute("event", event);
				return false;*/
				error = true;
			}
    	} catch (NullPointerException e) {
    		error = true;
    	}
    	if (error) {
    		redirectAttributes.addFlashAttribute("message", "Formularz zawiera błędy");
    		redirectAttributes.addFlashAttribute("event", event);
    		return false;
    	}
    	return true;
    }
    
    
    @RequestMapping(value = "/cities/search")
	public @ResponseBody List<String> searchCities(@RequestParam("cityName") String cityName) {
		List<String> cities = cityService.filterCities(cityName);
		return cities;
	}
    
    @RequestMapping(value = "/cities/check-if-exists")
	public @ResponseBody boolean checkIfCityExists(@RequestParam("cityName") String cityName) {
		boolean cityExists = cityService.checkIfCityExists(cityName);
		return cityExists;
	}
    
}
