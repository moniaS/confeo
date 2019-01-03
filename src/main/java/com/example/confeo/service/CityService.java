package com.example.confeo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.confeo.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository cityRepository;
	
	public List<String> findAllCities(){
		return cityRepository.findDistinctCityNames();
	}
	
	public List<String> filterCities(String name){
		return cityRepository.filterCityNames(name.toLowerCase());
	}
	
}
