package com.example.confeo.repository;

import com.example.confeo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select distinct function('month', e.startDate) from Event e where function('month', e.startDate) >= :currentMonth" +
            " order by function('month', e.startDate)")
    List<Integer> findUpcomingEventMonths(@Param("currentMonth") int currentMonth);

    @Query("select e from Event e where function('month', e.startDate) = :month")
    List<Event> findByStartDateMonth(@Param("month") int month);
}
