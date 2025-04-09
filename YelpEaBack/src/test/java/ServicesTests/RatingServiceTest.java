package ServicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.YelpEaBack.Entities.Rating;
import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Repositories.RatingRepository;
import edu.esiea.YelpEaBack.Services.RatingService;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository repo;

    @InjectMocks
    private RatingService service;
    
    // Test de création d'une Rating
    @Test
    void testCreate() {
        Customer customer = new Customer("customer1", "pass1");
        Restaurant restaurant = new Restaurant("Resto1", "Address1", "Phone1", "Description1", null);

        Rating ratingToCreate = new Rating(customer, restaurant, 4);

        when(repo.save(ratingToCreate)).thenReturn(ratingToCreate);

        Rating createdRating = service.create(ratingToCreate);

        assertNotNull(createdRating);
        assertEquals(4, createdRating.getValue());
        verify(repo, times(1)).save(ratingToCreate);
    }
    
    // Test de mise à jour d'une Rating existante
    @Test
    void testUpdate() {
        Customer customer = new Customer("customer1", "pass1");
        Restaurant restaurant = new Restaurant("Resto1", "Address1", "Phone1", "Description1", null);
        Rating existingRating = new Rating(customer, restaurant, 3);

        when(repo.findById(1)).thenReturn(Optional.of(existingRating));

        Rating updatedRating = new Rating(customer, restaurant, 5);

        when(repo.save(existingRating)).thenReturn(existingRating);

        Rating result = service.update(1, updatedRating);

        assertNotNull(result);
        assertEquals(5, result.getValue());
        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).save(existingRating);
    }
    
    // Test de suppression d'une Rating existante
    @Test
    void testDelete() {
        when(repo.existsById(1)).thenReturn(true);

        service.delete(1);
        verify(repo, times(1)).deleteById(1);
    }
    
    // Test de suppression d'une Rating inexistante
    @Test
    void testDeleteNotFound() {
        when(repo.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.delete(1);
        });
        assertEquals("Rating not found", exception.getMessage());

        verify(repo, never()).deleteById(anyInt());
    }
}
