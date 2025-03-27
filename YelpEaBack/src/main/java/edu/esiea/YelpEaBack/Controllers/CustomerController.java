package edu.esiea.YelpEaBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private final CustomerService service;
	
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomer();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok(service.getCustomerbyId(id));
    }
	
	@PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = service.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
