package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Mascota;
import com.luv2code.springboot.cruddemo.service.MascotaService;

@RestController
@RequestMapping("/rest")
public class MascotaRestController {

	private MascotaService mascotaService;
	
	@Autowired
	public MascotaRestController(MascotaService themascotaService) {
		mascotaService = themascotaService;
	}
	
	// expose "/mascotas" and return list of mascotas
	@GetMapping("/mascotas")
	public List<Mascota> findAll() {
		return mascotaService.findAll();
	}

	// add mapping for GET /employees/{mascotaId}
	
	@GetMapping("/mascotas/{mascotaId}")
	public Mascota getMascotas(@PathVariable int mascotaId) throws Exception {
		
		Mascota theMascota = mascotaService.findById(mascotaId);
	System.out.println(mascotaId);	
	System.out.println(theMascota);
		if (theMascota == null) {
			throw new Exception("Mascota id not found - " + mascotaId);
		}
		
		return theMascota;
	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/mascotas")
	public Mascota addMascota(@RequestBody Mascota theMascota) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theMascota.setId(0);
		
		mascotaService.save(theMascota);
		
		return theMascota;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/mascotas")
	public Mascota updateMascota(@RequestBody Mascota theMascota) {
		
		mascotaService.save(theMascota);
		
		return theMascota;
	}
	
	// add mapping for DELETE /employees/{mascotaId} - delete employee
	
	@DeleteMapping("/mascotas/{mascotaId}")
	public String deleteMascota(@PathVariable int mascotaId) {
		
		Mascota tempMascota = mascotaService.findById(mascotaId);
		
		// throw exception if null
		
		if (tempMascota == null) {
			throw new RuntimeException("Mascota id not found - " + mascotaId);
		}
		
		mascotaService.deleteById(mascotaId);
		
		return "Deleted mascota id - " + mascotaId;
	}
	
}










