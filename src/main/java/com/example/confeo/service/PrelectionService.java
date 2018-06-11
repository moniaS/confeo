package com.example.confeo.service;

import com.example.confeo.model.Event;
import com.example.confeo.model.Prelection;
import com.example.confeo.model.PrelectionStatus;
import com.example.confeo.model.User;
import com.example.confeo.repository.EventRepository;
import com.example.confeo.repository.PrelectionRepository;
import com.example.confeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mstobieniecka on 2018-06-11.
 */
@Service
public class PrelectionService {
    private final PrelectionRepository prelectionRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public PrelectionService(PrelectionRepository prelectionRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.prelectionRepository = prelectionRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Prelection addPrelection(Prelection prelection, Long eventId, String username) {
        Prelection prelectionToSave = new Prelection();
        Event event = eventRepository.getOne(eventId);
        User user = userRepository.findByEmail(username);
        prelectionToSave.setDescription(prelection.getDescription());
        prelectionToSave.setName(prelection.getName());
        prelectionToSave.setEvent(event);
        prelectionToSave.setUser(user);
        prelectionToSave.setStatus(PrelectionStatus.WAITING);
        return prelectionRepository.save(prelectionToSave);
    }

    public Prelection findPrelection(long id) {
        return prelectionRepository.getOne(id);
    }
}
