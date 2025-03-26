package edu.esiea.YelpEaBack.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private final CustomerRepository repo;
	
	public CustomerService(CustomerRepository repo) {
		this.repo = repo ;
	}
	
	public List<Customer> getAll() {
		return repo.findAll();
	}
	
	public Customer get(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

}
