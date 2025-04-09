package ServicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Repositories.CustomerRepository;
import edu.esiea.YelpEaBack.Services.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repo;

    @InjectMocks
    private CustomerService service;
    
    // Test de récupération de tous les Customers
    @Test
    void testGetAllCustomer() {
        // Préparation des données simulées
        Customer customer1 = new Customer("customer1", "pass1");
        Customer customer2 = new Customer("customer2", "pass2");
        List<Customer> customers = Arrays.asList(customer1, customer2);
        
        when(repo.findAll()).thenReturn(customers);
        
        // Exécution
        List<Customer> result = service.getAllCustomer();
        
        // Vérifications
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("customer1", result.get(0).getUsername());
        assertEquals("customer2", result.get(1).getUsername());
        
        verify(repo, times(1)).findAll();
    }
    
    // Test de récupération d'un Customer par son id
    @Test
    void testGetCustomerbyId() {
        Customer customer = new Customer("customer1", "pass1");
        when(repo.findById(1)).thenReturn(Optional.of(customer));
        
        Customer result = service.getCustomerbyId(1);
        
        assertNotNull(result);
        assertEquals("customer1", result.getUsername());
        verify(repo, times(1)).findById(1);
    }
    
    // Test de création d'un Customer
    @Test
    void testCreateCustomer() {
        Customer customer = new Customer("customer1", "pass1");
        when(repo.save(customer)).thenReturn(customer);
        
        Customer result = service.createCustomer(customer);
        
        assertNotNull(result);
        assertEquals("customer1", result.getUsername());
        verify(repo, times(1)).save(customer);
    }
    
    // Test de suppression d'un Customer existant
    @Test
    void testDeleteCustomer() {
        when(repo.existsById(1)).thenReturn(true);
        
        service.deleteCustomer(1);
        
        verify(repo, times(1)).deleteById(1);
    }
    
    // Test de suppression d'un Customer inexistant
    @Test
    void testDeleteCustomerNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.deleteCustomer(1);
        });
        assertEquals("Customer not found", exception.getMessage());
        
        verify(repo, never()).deleteById(anyInt());
    }
}
