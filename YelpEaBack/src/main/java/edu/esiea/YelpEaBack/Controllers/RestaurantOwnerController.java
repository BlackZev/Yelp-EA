package edu.esiea.YelpEaBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Entities.RestaurantOwner;
import edu.esiea.YelpEaBack.Services.RestaurantOwnerService;

@RestController
@RequestMapping("/RestaurantOwner")
public class RestaurantOwnerController {
	
	@Autowired
	private final  RestaurantOwnerService service;
	
	public RestaurantOwnerController(RestaurantOwnerService service) {
		this.service = service;
	}
	
	@PostMapping
	 public ResponseEntity<RestaurantOwner> createRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner){
		RestaurantOwner createdRestaurantOwner = service.create(restaurantOwner);
		 return ResponseEntity.ok(createdRestaurantOwner);
	 }
	
	
	@GetMapping("/all")
    public List<RestaurantOwner> getAllRestaurantOwner() {
        return service.getAll();
    }

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantOwner(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
	}
}
