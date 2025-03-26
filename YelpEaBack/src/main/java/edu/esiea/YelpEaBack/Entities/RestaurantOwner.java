package edu.esiea.YelpEaBack.Entities;

import java.util.ArrayList;
import java.util.List;

import edu.esiea.YelpEaBack.Entities.Abstract.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RestaurantOwner extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Identity
	int id;
	private List<Restaurant> restaurants;
	
	public RestaurantOwner(String username, String password) {
		super(username, password);
		this.restaurants = new ArrayList<>();
	}
	
	public List<Restaurant> getRestaurants(){
		return restaurants;
	}
	
	
	//méthode création Restaurant
	
	//méthode modification Restaurant 
	
	//méthode suppression Restaurant 
}
