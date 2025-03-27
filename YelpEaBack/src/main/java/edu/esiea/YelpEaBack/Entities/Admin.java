package edu.esiea.YelpEaBack.Entities;

import edu.esiea.YelpEaBack.Entities.Abstract.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Identity
	int id;
	
	public Admin(String username, String password) {
		super(username, password);
	}
}
