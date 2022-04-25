package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Plant;

public interface PlantService {
	
	Plant save(Plant plant);

	List<Plant> findAll();
	
	Plant findById(Long id);

	void deleteById(Long id);
}
