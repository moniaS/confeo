package com.example.confeo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.confeo.model.City;


public interface CityRepository extends JpaRepository<City, Long> {
    @Query("select distinct c.nazwa from City c")
    List<String> findDistinctCityNames();
    
    @Query("select distinct c.nazwa from City c where c.nazwa like :name")
    List<String> filterCityNames(@Param("name") String name);
}