package edu.esiea.YelpEaBack.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.esiea.YelpEaBack.Entities.Customer;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {

}
