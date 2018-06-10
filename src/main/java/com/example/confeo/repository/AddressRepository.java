package com.example.confeo.repository;

import com.example.confeo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by mstobieniecka on 2018-06-09.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select distinct a.cityName from Address a")
    List<String> findDistinctCityNames();
}
