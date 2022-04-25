package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Plant;
import com.adminportal.service.PlantService;

@Controller
@RequestMapping("/plant")
public class PlantController {

	@Autowired
	private PlantService plantService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPlant(Model model) {
		Plant plant = new Plant();
		model.addAttribute("plant", plant);
		return "addPlant";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPlantPost(@ModelAttribute("plant") Plant plant, @RequestParam MultipartFile plantImageFile, HttpServletRequest request) {
		try {
			byte[] imageFileBytes = plantImageFile.getBytes();
			plant.setPlantImage(imageFileBytes);
			plantService.save(plant);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return "redirect:plantList";
	}
	
	@RequestMapping("/plantInfo")
	public String plantInfo(@RequestParam("id") Long id, Model model) {
		Plant plant = plantService.findById(id);
		model.addAttribute("plant", plant);
		
		return "plantInfo";
	}

	@GetMapping("/image/{id}")
	@ResponseBody
	public void plantImage(@PathVariable("id") Long id, HttpServletResponse response) {
		Plant plant = plantService.findById(id);
		if(plant != null && plant.getPlantImage() != null) {
			try {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(plant.getPlantImage());
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping("/updatePlant")
	public String updatePlant(@RequestParam("id") Long id, Model model) {
		Plant plant = plantService.findById(id);
		model.addAttribute("plant", plant);
		
		return "updatePlant";
	}
	
	
	@RequestMapping(value="/updatePlant", method=RequestMethod.POST)
	public String updatePlantPost(@ModelAttribute("plant") Plant plant, @RequestParam MultipartFile  plantImageFile, HttpServletRequest request) {
		if(!plantImageFile.isEmpty()) {
			try {
				byte[] bytes = plantImageFile.getBytes();
				if(bytes != null && bytes.length > 0){
					plant.setPlantImage(bytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		plantService.save(plant);

		return "redirect:/plant/plantInfo?id="+plant.getId();
	}
	
	@RequestMapping("/plantList")
	public String plantList(Model model) {
		List<Plant> plantList = plantService.findAll();
		model.addAttribute("plantList", plantList);
		return "plantList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		plantService.deleteById(Long.parseLong(id.substring(8)));
		List<Plant> plantList = plantService.findAll();
		model.addAttribute("plantList", plantList);
		
		return "redirect:/plant/plantList";
	}

}
