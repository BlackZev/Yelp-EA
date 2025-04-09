package edu.esiea.YelpEaBack.Entities;

import edu.esiea.YelpEaBack.Entities.Abstract.User;
import jakarta.persistence.Entity;


@Entity
public class Admin extends User {


	
	public Admin(String username, String password) {
		super(username, password);

	}
}
