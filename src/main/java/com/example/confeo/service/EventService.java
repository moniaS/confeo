package com.example.confeo.service;

import com.example.confeo.model.Event;
import com.example.confeo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mstobieniecka on 2018-05-29.
 */
@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Map<String, List<Event>> findEventsByMonth() {
        List<Integer> months = eventRepository.findUpcomingEventMonths(LocalDate.now().getMonthValue());
        Map<String, List<Event>> eventsByMonth = new LinkedHashMap<>();
        for (int month : months) {
            if (month == LocalDate.now().getMonthValue()) {
                eventsByMonth.put(convertMonthToString(month), eventRepository.findByStartDateMonth(month)
                        .stream()
                        .filter(event -> event.getStartDate().isAfter(LocalDate.now()))
                        .collect(Collectors.toList()));
            }
            eventsByMonth.put(convertMonthToString(month), eventRepository.findByStartDateMonth(month));
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
    	eventRepository.save(event);
    }
}
