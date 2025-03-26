package edu.esiea.YelpEaBack.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Rating {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Identity
	int id;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Customer customer;
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Restaurant restaurant;
	private int value;
	
	public Rating(Customer customer, Restaurant restaurant,int value) {
		if (value < 1 || value >5 ) {
			throw new IllegalArgumentException("La note doit être comprise entre 1 et 5.");
		}
		
		this.customer = customer;
		this.restaurant = restaurant;
		this.value = value;
		
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
	    if (value < 1 || value > 5) {
	        throw new IllegalArgumentException("La note doit être comprise entre 1 et 5.");
	    }
	    this.value = value;
	}
	
	public Customer getClient() {
		return customer;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
