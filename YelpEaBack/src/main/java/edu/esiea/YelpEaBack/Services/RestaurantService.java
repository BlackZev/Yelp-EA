package edu.esiea.YelpEaBack.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.YelpEaBack.Entities.Rating;
import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Repositories.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private final RestaurantRepository repo;
	
	public RestaurantService(RestaurantRepository repo) {
		this.repo = repo ;
	}
	
	// récupérer tous les resto
	public List<Restaurant> getAll() {
		return repo.findAll();
	}
	
	// récupérer un resto précis 
	public Restaurant get(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}
	
	// Créer un restaurant
	public Restaurant create(Restaurant restaurant) {
		return repo.save(restaurant);
	}
	
	// Mettre à jour un restaurant
	public Restaurant update(int id, Restaurant updatedRestaurant) {
		Restaurant restaurant = repo.findById(id)
			.orElseThrow(() -> new RuntimeException("Restaurant not found"));
		restaurant.setName(updatedRestaurant.getName());
		restaurant.setAddress(updatedRestaurant.getAddress());
		restaurant.setPhone(updatedRestaurant.getPhone());
		restaurant.setDescription(updatedRestaurant.getDescription());
		restaurant.setType(updatedRestaurant.getType());
		return repo.save(restaurant);
	}
	
	// Supprimer un restaurant
	public void delete(int id) {
		if (!repo.existsById(id)) {
			throw new RuntimeException("Restaurant not found");
		}
		repo.deleteById(id);
	}
		
	// Calculer la moyenne des ratings d'un restaurant
	
	public double calculateAverageRating(int id) {
		Restaurant restaurant = get(id);
		List<Rating> ratings = restaurant.getRatings();
		if (ratings == null || ratings.isEmpty()) {
			return 0.0;
		}
		double sum = 0.0;
		for (Rating rate : ratings) {
			sum += rate.getValue();
		}
		return sum / ratings.size();
	}
}
