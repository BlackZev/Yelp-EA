package edu.esiea.YelpEaBack.Entities;

import java.util.ArrayList;
import java.util.List;

import edu.esiea.YelpEaBack.Enum.TypeRestauEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Identity
	int id;
	
	private String name;
	private String address;
	private String phone;
	private String description;
	private TypeRestauEnum type;
	private List<Rating> ratings;
	
	@Transient
	private double avg;
	
	
    public Restaurant(String name, String address, String phone, String description, TypeRestauEnum type) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.type = type;
        this.ratings = new ArrayList<>();
    }
    
    // Getters et setters pour les informations du restaurant
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeRestauEnum getType() {
        return type;
    }

    public void setType(TypeRestauEnum type) {
        this.type = type;
    }
    
    public List<Rating> getRatings(){
    	return ratings;
    }
    
    
    public void setAvg(double avg) {
        this.avg = avg;
    }
    
    public double getAvg() {
    	return avg;
    }
    
}
