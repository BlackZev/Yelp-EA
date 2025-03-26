package edu.esiea.YelpEaBack.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.esiea.YelpEaBack.Entities.RestaurantOwner;

public interface RestaurantOwnerRepository extends JpaRepository <RestaurantOwner,Integer> {

}
