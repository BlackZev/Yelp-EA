package edu.esiea.YelpEaBack.Entities.Abstract;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
	protected String username;
	protected String password;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Identity
	int id;
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    public int getId() {
    	return id;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
