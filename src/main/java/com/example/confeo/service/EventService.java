package com.example.confeo.service;

import com.example.confeo.exception.CannotSignUpOnCanceledEvent;
import com.example.confeo.exception.ParticipantsLimitReached;
import com.example.confeo.model.Category;
import com.example.confeo.model.Event;
import com.example.confeo.model.EventStatus;
import com.example.confeo.model.User;
import com.example.confeo.repository.AddressRepository;
import com.example.confeo.repository.CategoryRepository;
import com.example.confeo.repository.EventRepository;
import com.example.confeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Created by mstobieniecka on 2018-05-29.
 */
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, AddressRepository addressRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.addressRepository = addressRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Map<String, List<Event>> findEventsByMonth(String name, String city, String category, LocalDate startDate, LocalDate endDate) {
        List<Integer> months = eventRepository.findUpcomingEventMonths(name, city, category, LocalDate.now().getMonthValue());
        Map<String, List<Event>> eventsByMonth = new LinkedHashMap<>();
        for (int month : months) {
            if (month == LocalDate.now().getMonthValue()) {
                eventsByMonth.put(convertMonthToString(month), eventRepository.findEvents(name, city, category, month)
                        .stream()
                        .filter(event -> event.getStartDate().isAfter(LocalDate.now()))
                        .collect(Collectors.toList()));
            }
            eventsByMonth.put(convertMonthToString(month), eventRepository.findEvents(name, city, category, month));
        }
        checkEventDates(eventsByMonth, startDate, endDate);
        return eventsByMonth;
    }

    public void cancelEvent(Long id) {
        Event event = findById(id);
        event.setStatus(EventStatus.CANCELED);
        eventRepository.save(event);
    }


    public void signUpOnEventAsParticipant(String username, Long eventId) throws ParticipantsLimitReached, CannotSignUpOnCanceledEvent {
        Event event = eventRepository.getOne(eventId);
        User user = userRepository.findByEmail(username);
        if(event.getUsers().size() < event.getMaxParticipants()) {
            if(event.getStatus().equals(EventStatus.UPCOMING)) {
                event.getUsers().add(user);
                eventRepository.save(event);
            }
            else {
                throw new CannotSignUpOnCanceledEvent();
            }
        } else {
            throw new ParticipantsLimitReached();
        }
    }

    public void signOffFromEventAsParticipant(String username, Long eventId) {
        Event event = eventRepository.getOne(eventId);
        User user = userRepository.findByEmail(username);
        event.getUsers().remove(user);
        eventRepository.save(event);
    }

    public boolean isUserSignedUpOnEventAsParticipant(String username, Long eventId) {
        AtomicBoolean isSignedUp = new AtomicBoolean(false);
        User user = userRepository.findByEmail(username);
        Event event = eventRepository.getOne(eventId);
        event.getUsers().forEach(u -> {
            if (u.equals(user)) {
                isSignedUp.set(true);
            }
        });
        return isSignedUp.get();
    }

    public boolean isUserSignedUpOnEventAsPrelegent(String username, Long eventId) {
        AtomicBoolean isSignedUp = new AtomicBoolean(false);
        User user = userRepository.findByEmail(username);
        Event event = eventRepository.getOne(eventId);
        event.getPrelections().forEach(prelection -> {
            if(prelection.getUser().getEmail().equals(username)) {
                isSignedUp.set(true);
            }
        });
        return isSignedUp.get();
    }

    public Event findEvent(Long id) {
        return eventRepository.getOne(id);
    }

    public List<String> findCities() {
        return addressRepository.findDistinctCityNames();
    }

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public List<Event> findUpcomingEventsForHomepage() {
        List<Event> events = eventRepository.findByStartDateAfter(LocalDate.now());
        events.sort(Comparator.comparing(Event::getStartDate));
        if(events.size() < 5) {
            return events;
        } else {
            return events.subList(0, 5);
        }
    }

    public List<Event> findFinishedEventsForHomepage() {
        List<Event> events = eventRepository.findByEndDateBefore(LocalDate.now());
        events.sort(Comparator.comparing(Event::getEndDate));
        Collections.reverse(events);
        if(events.size() < 5) {
            return events;
        } else {
            return events.subList(0, 5);
        }
    }

    private Map<String, List<Event>> checkEventDates(Map<String, List<Event>> eventsByMonth, LocalDate startDate, LocalDate endDate) {

        if(startDate != null) {
            for(Map.Entry<String, List<Event>> entry: eventsByMonth.entrySet()) {
                List<Event> events = eventsByMonth.get(entry.getKey()).stream()
                        .filter(e -> e.getStartDate().isAfter(startDate)).collect(Collectors.toList());
                entry.setValue(events);
            }
        }

        if(endDate != null) {
            for(Map.Entry<String, List<Event>> entry: eventsByMonth.entrySet()) {
                List<Event> events = eventsByMonth.get(entry.getKey()).stream()
                        .filter(e -> e.getStartDate().isBefore(endDate)).collect(Collectors.toList());
                entry.setValue(events);
            }
        }
        return eventsByMonth;
    }

    private String convertMonthToString(int month) {
        switch (month) {
            case 1:
                return "Styczeń";
            case 2:
                return "Luty";
            case 3:
                return "Marzec";
            case 4:
                return "Kwiecień";
            case 5:
                return "Maj";
            case 6:
                return "Czerwiec";
            case 7:
                return "Lipiec";
            case 8:
                return "Sierpień";
            case 9:
                return "Wrzesień";
            case 10:
                return "Październik";
            case 11:
                return "Listopad";
            case 12:
                return "Grudzień";
            default:
                return "Styczeń";
        }
    }
    
    public void saveEvent(Event event) {
    	if (event.getMaxParticipants() == null) event.setMaxParticipants(100);
    	event.setStatus(EventStatus.UPCOMING);
    	eventRepository.save(event);
    }

	public Event findById(long id) {
		try {
			return eventRepository.findById(id).get();
		} catch (Exception e){
			return null;
		}
		
	}
	
	public void updateEvent(Event updatedEvent) {
    	Event event = findById(updatedEvent.getId());
    	event.setAddress(updatedEvent.getAddress());
    	event.setCategory(updatedEvent.getCategory());
    	event.setDescription(updatedEvent.getDescription());
    	event.setEndDate(updatedEvent.getEndDate());
    	event.setIsFree(updatedEvent.getIsFree());
    	event.setMaxParticipants(updatedEvent.getMaxParticipants());
    	event.setName(updatedEvent.getName());
    	event.setPrelections(updatedEvent.getPrelections());
    	event.setPricePerParticipant(updatedEvent.getPricePerParticipant());
    	event.setPricePerPrelegent(updatedEvent.getPricePerPrelegent());
    	event.setStartDate(updatedEvent.getStartDate());
    	event.setType(updatedEvent.getType());
    	eventRepository.save(event);
    }
}
