package edu.esiea.YelpEaBack.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.YelpEaBack.Entities.Admin;
import edu.esiea.YelpEaBack.Repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private final AdminRepository repo;
	
	public AdminService(AdminRepository repo) {
		this.repo = repo ;
	}
		
	//créer un admin 
	public Admin createAdmin(Admin admin) {
		return repo.save(admin);
	}
	
    //supprimer un admin
	public void deleteAdmin(int id) {
		if (!repo.existsById(id)) {
			throw new RuntimeException("Admin not found");
		}
		repo.deleteById(id);
	}
}
