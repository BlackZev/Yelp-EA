package edu.esiea.YelpEaBack.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Services.RestaurantService;


@RestController
@RequestMapping("/Restaurant")
public class RestaurantController {
	
	@Autowired
	private final  RestaurantService service;
	
	public RestaurantController(RestaurantService service) {
		this.service = service;
	}
	
	@PostMapping
	 public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
		 Restaurant createdRestaurant = service.create(restaurant);
		 return ResponseEntity.ok(createdRestaurant);
	 }
	
	@GetMapping("/all")
    public List<Restaurant> getAllRestaurant() {
        return service.getAll();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
    }
	
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
    	Restaurant updatedRestaurant = service.update(id, restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
