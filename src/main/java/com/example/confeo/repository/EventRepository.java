package com.example.confeo.repository;

import com.example.confeo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
