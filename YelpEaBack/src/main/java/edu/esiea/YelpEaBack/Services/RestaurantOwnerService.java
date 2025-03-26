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
	
	//récupérer tous les propriétaire de resto
	public List<RestaurantOwner> getAll() {
		return repo.findAll();
	}
	
	
	// Creation d'un propriétaire de resto
	public RestaurantOwner create(RestaurantOwner restaurantOwner) {
		return repo.save(restaurantOwner);
	}
	
	//suppression d'un propriétaire de resto 

    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Restaurant Owner not found");
        }
        repo.deleteById(id);
    }
	

}