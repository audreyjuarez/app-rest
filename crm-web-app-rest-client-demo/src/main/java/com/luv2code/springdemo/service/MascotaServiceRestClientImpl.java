package com.luv2code.springdemo.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luv2code.springdemo.model.Mascota;

@Service
public class MascotaServiceRestClientImpl implements MascotaService {

	private RestTemplate restTemplate;

	private String crmRestUrl;
		
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public MascotaServiceRestClientImpl(RestTemplate theRestTemplate, 
										@Value("${crm.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		crmRestUrl = theUrl;
				
		logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
	}
	
	@Override
	public List<Mascota> getMascotas() {
		
		logger.info("***OBTENER LISTA DE CLIENTES DESDE EL SERVICE REST CLIENTE");
		logger.info("in getMascotas(): Calling REST API " + crmRestUrl);

		// make REST call
		ResponseEntity<List<Mascota>> responseEntity = 
											restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
													 new ParameterizedTypeReference<List<Mascota>>() {});

		// get the list of mascotas from response
		List<Mascota> mascotas = responseEntity.getBody();

		logger.info("in getMascotas(): mascotas" + mascotas);
		
		return mascotas;
	}

	@Override
	public Mascota getMascota(int theId) {
		logger.info("***OBTENER UN CLIENTE DESDE EL SERVICE REST CLIENTE");

		logger.info("in getMascota(): Calling REST API " + crmRestUrl);

		// make REST call
		Mascota theMascota = 
				restTemplate.getForObject(crmRestUrl + "/" + theId, 
						Mascota.class);

		logger.info("in saveMascota(): theMascota=" + theMascota);
		
		return theMascota;
	}

	@Override
	public void saveMascota(Mascota theMascota) {

		logger.info("in saveMascota(): Calling REST API " + crmRestUrl);
		
		int mascotaId = theMascota.getId();

		// make REST call
		if (mascotaId == 0) {
			// add employee
			logger.info("***SALVAR UN CLIENTE DESDE EL SERVICE REST CLIENTE");

			restTemplate.postForEntity(crmRestUrl, theMascota, String.class);			
		
		} else {
			// update employee
			logger.info("***ACTUALIZAR UN CLIENTE DESDE EL SERVICE REST CLIENTE");

			restTemplate.put(crmRestUrl, theMascota);
		}

		logger.info("in saveMascota(): success");	
	}

	@Override
	public void deleteMascota(int theId) {
		logger.info("***BORRAR UN CLIENTE DESDE EL SERVICE REST CLIENTE");

		logger.info("in deleteMascota(): Calling REST API " + crmRestUrl);

		// make REST call
		restTemplate.delete(crmRestUrl + "/" + theId);

		logger.info("in deleteMascota(): deleted mascota theId=" + theId);
	}

}
