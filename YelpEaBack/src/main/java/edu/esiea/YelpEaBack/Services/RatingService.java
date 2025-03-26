package edu.esiea.YelpEaBack.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.YelpEaBack.Entities.Rating;
import edu.esiea.YelpEaBack.Repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private final RatingRepository repo;
	
	public RatingService(RatingRepository repo) {
		this.repo = repo ;
	}
	
	public List<Rating> getAll() {
		return repo.findAll();
	}
	
	public Rating get(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

}
