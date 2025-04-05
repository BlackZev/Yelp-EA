package ServicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.YelpEaBack.Entities.RestaurantOwner;
import edu.esiea.YelpEaBack.Repositories.RestaurantOwnerRepository;
import edu.esiea.YelpEaBack.Services.RestaurantOwnerService;

@ExtendWith(MockitoExtension.class)
public class RestaurantOwnerServiceTest {

    @Mock
    private RestaurantOwnerRepository repo;

    @InjectMocks
    private RestaurantOwnerService service;
    
    // Test de récupération de tous les RestaurantOwner
    @Test
    void testGetAll() {
        // Création d'exemples
        RestaurantOwner owner1 = new RestaurantOwner(1,"owner1", "pass1");
        RestaurantOwner owner2 = new RestaurantOwner(2,"owner2", "pass2");
        List<RestaurantOwner> owners = Arrays.asList(owner1, owner2);

        when(repo.findAll()).thenReturn(owners);

        List<RestaurantOwner> result = service.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("owner1", result.get(0).getUsername());
        assertEquals("owner2", result.get(1).getUsername());
        verify(repo, times(1)).findAll();
    }
    
    // Test de création d'un RestaurantOwner
    @Test
    void testCreate() {
        RestaurantOwner owner = new RestaurantOwner(1,"owner1", "pass1");
        when(repo.save(owner)).thenReturn(owner);
        RestaurantOwner created = service.create(owner);
        assertNotNull(created);
        assertEquals("owner1", created.getUsername());
        verify(repo, times(1)).save(owner);
    }
    
    // Test de suppression d'un RestaurantOwner existant
    @Test
    void testDelete() {
        int id = 1;
        when(repo.existsById(id)).thenReturn(true);
        service.delete(id);
        verify(repo, times(1)).deleteById(id);
    }
    
    // Test de suppression d'un RestaurantOwner inexistant
    @Test
    void testDeleteNotFound() {
        int id = 1;
        when(repo.existsById(id)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.delete(id);
        });
        assertEquals("Restaurant Owner not found", exception.getMessage());
        verify(repo, never()).deleteById(anyInt());
    }
}
