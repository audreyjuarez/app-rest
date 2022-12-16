package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.MascotaDAO;
import com.luv2code.springboot.cruddemo.entity.Mascota;

@Service
public class MascotaServiceImpl implements MascotaService {

	
	private MascotaDAO mascotaDAO;
	
	@Autowired
	public MascotaServiceImpl(@Qualifier("mascotaDAOJdbcImpl") MascotaDAO theMascotaDAO) {
		mascotaDAO = theMascotaDAO;
	}
	
	@Override
	@Transactional
	public List<Mascota> findAll() {
		return mascotaDAO.findAll();
	}

	@Override
	@Transactional
	public Mascota findById(int theId) {
		return mascotaDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Mascota theMascota) {
		mascotaDAO.save(theMascota);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		mascotaDAO.deleteById(theId);
	}

}






