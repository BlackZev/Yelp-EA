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
	
	// récupérer tous les Customers
	public List<Customer> getAllCustomer() {
		return repo.findAll();
	}
	
	// récuperer un Customer par son id 
	public Customer getCustomerbyId(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	// créer un Customer 
	public Customer createCustomer (Customer customer) {
		return repo.save(customer);
	}

	// Supprimer un Customer 
	public void deleteCustomer(int id) {
		if (!repo.existsById(id)) {
			throw new RuntimeException("Customer not found");
		}
		repo.deleteById(id);
	}

}

