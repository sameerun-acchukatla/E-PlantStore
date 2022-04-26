package com.adminportal.service.impl;

import java.util.List;

import com.adminportal.domain.Plant;
import com.adminportal.repository.PlantRepository;
import com.adminportal.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public void update(Plant plant, MultipartFile plantImageFile) {

		Plant existingPlant = findById(plant.getId());
		if(!plantImageFile.isEmpty()) {
			try {
				byte[] bytes = plantImageFile.getBytes();
				if(bytes != null && bytes.length > 0){
					plant.setPlantImage(bytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			plant.setPlantImage(existingPlant.getPlantImage());
		}

		save(plant);
	}

	@Override
	public void deleteById(Long id) {
		plantRepository.deleteById(id);
	}
}
