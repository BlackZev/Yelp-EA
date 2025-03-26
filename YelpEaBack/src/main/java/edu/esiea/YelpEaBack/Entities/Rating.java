package edu.esiea.YelpEaBack.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Identity
	int id;
	
	private Customer customer;
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
	
	public Customer getClient() {
		return customer;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
