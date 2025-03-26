package edu.esiea.YelpEaBack.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.YelpEaBack.Entities.RestaurantOwner;
import edu.esiea.YelpEaBack.Repositories.RestaurantOwnerRepository;

@Service
public class RestaurantOwnerService {
	
	@Autowired
	private final RestaurantOwnerRepository repo;
	
	
	public RestaurantOwnerService(RestaurantOwnerRepository repo) {
		this.repo = repo ;
	}
	
	public List<RestaurantOwner> getAll() {
		return repo.findAll();
	}
	
	public RestaurantOwner get(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

}