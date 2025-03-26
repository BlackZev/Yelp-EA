package edu.esiea.YelpEaBack.Services;

import java.util.List;

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
	
	public List<Admin> getAll() {
		return repo.findAll();
	}
	
	public Admin get(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
	}
	
	

}
