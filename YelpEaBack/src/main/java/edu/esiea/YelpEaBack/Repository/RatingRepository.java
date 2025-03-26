package edu.esiea.YelpEaBack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.esiea.YelpEaBack.Entities.Rating;

public interface RatingRepository extends JpaRepository <Rating, Integer> {

}
