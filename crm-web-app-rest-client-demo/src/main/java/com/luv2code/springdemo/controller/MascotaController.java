package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.model.Mascota;
import com.luv2code.springdemo.service.MascotaService;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

	// need to inject our mascota service
	@Autowired
	private MascotaService mascotaService;
	
	@GetMapping("/list")
	public String listMascotas(Model theModel) {
		
		// get mascotas from the service
		List<Mascota> theMascotas = mascotaService.getMascotas();
				
		// add the mascotas to the model
		theModel.addAttribute("mascotas", theMascotas);
		
		return "list-mascotas";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Mascota theMascota = new Mascota();
		
		theModel.addAttribute("mascota", theMascota);
		
		return "mascota-form";
	}
	
	@PostMapping("/saveMascota")
	public String saveMascota(@ModelAttribute("mascota") Mascota theMascota) {
		
		// save the mascota using our service
		mascotaService.saveMascota(theMascota);	
		
		return "redirect:/mascota/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("mascotaId") int theId,
									Model theModel) {
		
		// get the mascota from our service
		Mascota theMascota = mascotaService.getMascota(theId);	
		
		// set mascota as a model attribute to pre-populate the form
		theModel.addAttribute("mascota", theMascota);
		
		// send over to our form		
		return "mascota-form";
	}
	
	@GetMapping("/delete")
	public String deleteMascota(@RequestParam("mascotaId") int theId) {
		
		// delete the mascota
		mascotaService.deleteMascota(theId);
		
		return "redirect:/mascota/list";
	}
}










