package com.luv2code.springboot.cruddemo.dao;

import java.util.List;
import com.luv2code.springboot.cruddemo.entity.Mascota;

public interface MascotaDAO {

	List<Mascota> findAll();
	
	Mascota findById(int theId);
	
	void save(Mascota theMascota);
	
	void deleteById(int theId);
	
}
