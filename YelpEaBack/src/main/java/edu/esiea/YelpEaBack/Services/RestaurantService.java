package edu.esiea.YelpEaBack.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Repositories.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private final RestaurantRepository repo;
	
	public RestaurantService(RestaurantRepository repo) {
		this.repo = repo ;
	}
	
	public List<Restaurant> getAll() {
		return repo.findAll();
	}
	
	public Restaurant get(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

}
