package edu.esiea.YelpEaBack.Services;

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

	// Creation de la Note
	public Rating create(Rating rating) {
		return repo.save(rating);
	}
	
	// Mise à jour de la Note
    public Rating update(int id, Rating updatedRating) {
        Rating rating = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        rating.setValue(updatedRating.getValue());
        return repo.save(rating);
    }
    
    // Suppression Note
    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Rating not found");
        }
        repo.deleteById(id);
    }

	
}
