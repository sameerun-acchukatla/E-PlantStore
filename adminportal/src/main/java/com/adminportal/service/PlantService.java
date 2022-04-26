package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Plant;
import org.springframework.web.multipart.MultipartFile;

public interface PlantService {
	
	Plant save(Plant plant);

	List<Plant> findAll();
	
	Plant findById(Long id);

	void update(Plant plant, MultipartFile plantImageFile);

	void deleteById(Long id);
}
