package edu.esiea.YelpEaBack.Entities;

import edu.esiea.YelpEaBack.Entities.Abstract.User;
import jakarta.persistence.Entity;

@Entity
public class Customer extends User {


	
	public Customer(String username, String password) {
		super(username, password);
	}
}
