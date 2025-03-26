package edu.esiea.YelpEaBack.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.esiea.YelpEaBack.Entities.Restaurant;

public interface RestaurantRepository extends JpaRepository <Restaurant , Integer>  {

}
