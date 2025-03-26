package edu.esiea.YelpEaBack.Entities.Abstract;

public abstract class User {
	protected String username;
	protected String password;
	
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    // méthode de login
   public String getUsername() {
    	return username;
    }

}
