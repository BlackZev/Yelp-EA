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
import edu.esiea.YelpEaBack.Entities.Rating;
import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Enum.TypeRestauEnum;
import edu.esiea.YelpEaBack.Repositories.RestaurantRepository;
import edu.esiea.YelpEaBack.Services.RestaurantService;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository repo;

    @InjectMocks
    private RestaurantService service;
    
    // Test de la méthode getAll() : la moyenne des ratings est calculée pour chaque restaurant.
    @Test
    void testGetAll() {
        Restaurant r1 = new Restaurant("Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.Italien);
        Customer dummyCustomer = new Customer(1,"customer", "customerPass");
        Rating rating1 = new Rating(dummyCustomer, r1, 4);
        r1.getRatings().add(rating1);
        
        Restaurant r2 = new Restaurant("Resto2", "Address2", "Phone2", "Desc2", TypeRestauEnum.Italien);
        
        List<Restaurant> restaurants = Arrays.asList(r1, r2);
        when(repo.findAll()).thenReturn(restaurants);
        
        List<Restaurant> result = service.getAll();

        assertEquals(4.0, result.get(0).getAvg());
        assertEquals(0.0, result.get(1).getAvg());
        verify(repo, times(1)).findAll();
    }
    
    // Test de la méthode get(int id) qui calcule la moyenne après récupération.
    @Test
    void testGet() {
        Restaurant r = new Restaurant("Resto", "Address", "Phone", "Desc", TypeRestauEnum.Chinois);
        Customer dummyCustomer = new Customer(1,"customer", "custommerPass");
        Rating rating = new Rating(dummyCustomer, r, 5);
        r.getRatings().add(rating);
        
        when(repo.findById(1)).thenReturn(Optional.of(r));
        Restaurant result = service.get(1);
        
        assertNotNull(result);
        assertEquals("Resto", result.getName());
        assertEquals(5.0, result.getAvg());
        verify(repo, times(1)).findById(1);
    }
    
    // Test de la méthode create(Restaurant)
    @Test
    void testCreate() {
        Restaurant r = new Restaurant("Resto", "Address", "Phone", "Desc", TypeRestauEnum.Chinois);
        when(repo.save(r)).thenReturn(r);
        
        Restaurant result = service.create(r);
        assertNotNull(result);
        assertEquals("Resto", result.getName());
        verify(repo, times(1)).save(r);
    }
    
    // Test de la méthode update(int id, Restaurant)
    @Test
    void testUpdate() {
        Restaurant existing = new Restaurant("Resto", "Address", "Phone", "Desc", TypeRestauEnum.Chinois);
        when(repo.findById(1)).thenReturn(Optional.of(existing));
        
        Restaurant updated = new Restaurant("NewResto", "NewAddress", "NewPhone", "NewDesc", TypeRestauEnum.FastFood);
        when(repo.save(existing)).thenReturn(existing);
        
        Restaurant result = service.update(1, updated);
        assertNotNull(result);
        assertEquals("NewResto", result.getName());
        assertEquals("NewAddress", result.getAddress());
        assertEquals("NewPhone", result.getPhone());
        assertEquals("NewDesc", result.getDescription());
        assertEquals(TypeRestauEnum.FastFood, result.getType());
        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).save(existing);
    }
    
    // Test de la méthode delete(int id) pour un restaurant existant.
    @Test
    void testDelete() {
        when(repo.existsById(1)).thenReturn(true);
        service.delete(1);
        verify(repo, times(1)).deleteById(1);
    }
    
    // Test de la méthode delete(int id) pour un restaurant inexistant.
    @Test
    void testDeleteNotFound() {
        int id = 1;
        when(repo.existsById(id)).thenReturn(false);
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.delete(id);
        });
        assertEquals("Restaurant not found", exception.getMessage());
        verify(repo, never()).deleteById(anyInt());
    }
    
    // Test de la méthode calculateAverageRating(Restaurant)
    @Test
    void testCalculateAverageRating() {
        Restaurant r = new Restaurant("Resto", "Address", "Phone", "Desc", TypeRestauEnum.Chinois);
        service.calculateAverageRating(r);
        assertEquals(0.0, r.getAvg());
        
        Customer dummyCustomer = new Customer(1,"Customer", "CustomerPass");
        Rating rating1 = new Rating(dummyCustomer, r, 4);
        Rating rating2 = new Rating(dummyCustomer, r, 5);
        r.getRatings().add(rating1);
        r.getRatings().add(rating2);
        service.calculateAverageRating(r);
        assertEquals(4.5, r.getAvg());
    }
}
