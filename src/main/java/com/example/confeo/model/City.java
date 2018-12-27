package com.example.confeo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class City {

	
	private Integer woj;
	private Integer pow;
	private Integer gmi;
	private Integer rgmi;
	private Integer rm;
	private Integer mz;
	private String nazwa;
	@Id
	private Integer sym;
	private Integer sympod_stan;
}
