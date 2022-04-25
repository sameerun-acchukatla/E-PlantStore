package com.adminportal.service.impl;

import java.util.List;

import com.adminportal.domain.Plant;
import com.adminportal.repository.PlantRepository;
import com.adminportal.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImpl implements PlantService {
	
	@Autowired
	private PlantRepository plantRepository;
	

	@Override
	public Plant save(Plant plant) {
		return plantRepository.save(plant);
	}

	public List<Plant> findAll() {
		return (List<Plant>) plantRepository.findAll();
	}
	
	public Plant findById(Long id) {
		return plantRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		plantRepository.deleteById(id);
	}
}
