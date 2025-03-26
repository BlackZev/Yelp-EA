package edu.esiea.YelpEaBack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.esiea.YelpEaBack.Entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
