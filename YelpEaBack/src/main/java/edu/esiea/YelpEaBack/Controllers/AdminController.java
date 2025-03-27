package edu.esiea.YelpEaBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.YelpEaBack.Entities.Admin;
import edu.esiea.YelpEaBack.Services.AdminService;

@RestController
@RequestMapping("/paneladmin")
public class AdminController {
	
	@Autowired
	private final AdminService service;
	
	public AdminController(AdminService service) {
		this.service = service;
	}
	
 @PostMapping
 public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
	 Admin createdAdmin = service.createAdmin(admin);
	 return ResponseEntity.ok(createdAdmin);
 }
 
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteAdmin(@PathVariable int id){
	 service.deleteAdmin(id);
	 return ResponseEntity.noContent().build();
 }
	
	

}
