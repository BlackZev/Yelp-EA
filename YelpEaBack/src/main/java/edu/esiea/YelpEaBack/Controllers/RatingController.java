package edu.esiea.YelpEaBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.esiea.YelpEaBack.Entities.Rating;
import edu.esiea.YelpEaBack.Services.RatingService;

@RestController
@RequestMapping("/Rating")
public class RatingController {
	
	@Autowired
	private final RatingService service;
	
	public RatingController(RatingService service) {
		this.service = service;
	}
	
	
	@PostMapping
	 public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		 Rating createdRating = service.create(rating);
		 return ResponseEntity.ok(createdRating);
	 }
	
	
    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable int id, @RequestBody Rating rating) {
    	Rating updatedRating = service.update(id, rating);
        return ResponseEntity.ok(updatedRating);
    }
	
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable int id){
   	 service.delete(id);
   	 return ResponseEntity.noContent().build();
    }
	

}
