package edu.esiea.YelpEaBack.Entities;

import java.util.ArrayList;
import java.util.List;

import edu.esiea.YelpEaBack.Entities.Abstract.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class RestaurantOwner extends User {
	

	
	@OneToMany(mappedBy = "restaurateur", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Restaurant> restaurants;

	
	public RestaurantOwner(String username, String password) {
		super(username, password);

		this.restaurants = new ArrayList<>();
	}

	public List<Restaurant> getRestaurants(){
		return restaurants;
	}
	
}
