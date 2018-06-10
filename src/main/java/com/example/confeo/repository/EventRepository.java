package com.example.confeo.repository;

import com.example.confeo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Created by mstobieniecka on 2018-05-26.
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select distinct function('month', e.startDate) from Event e where function('month', e.startDate) >= :currentMonth" +
            " and e.name like :name and e.address.cityName like :city and e.category.name like:category order by function('month', e.startDate)")
    List<Integer> findUpcomingEventMonths(@Param("name") String name, @Param("city") String city,
                                          @Param("category") String category, @Param("currentMonth") int currentMonth);

    @Query("select e from Event e where e.address.cityName like :city and e.name like :name and e.category.name like :category and function('month', e.startDate) = :month")
    List<Event> findEvents(@Param("name") String name, @Param("city") String city,
                                                              @Param("category") String category, @Param("month") int month);
}
