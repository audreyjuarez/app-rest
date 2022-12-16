package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Mascota;

public interface MascotaService {

	public List<Mascota> findAll();
	
	public Mascota findById(int theId);
	
	public void save(Mascota theMascota);
	
	public void deleteById(int theId);
	
}
