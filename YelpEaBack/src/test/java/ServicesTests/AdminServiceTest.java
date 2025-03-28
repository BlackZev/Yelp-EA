package ServicesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import edu.esiea.YelpEaBack.Entities.Admin;
import edu.esiea.YelpEaBack.Repositories.AdminRepository;
import edu.esiea.YelpEaBack.Services.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    
    @Mock
    private AdminRepository repo;
    
    @InjectMocks
    private AdminService service;
    
    @Test
    void testCreateAdmin() {
        Admin adminToCreate = new Admin("AdminUser", "AdminPassmord");
        Admin savedAdmin = new Admin("AdminUser", "AdminPassmord");
        ReflectionTestUtils.setField(savedAdmin, "id", 1);
        
        when(repo.save(adminToCreate)).thenReturn(savedAdmin);
        
        Admin result = service.createAdmin(adminToCreate);
        
        assertNotNull(result);
        assertEquals("AdminUser", result.getUsername());
        assertEquals("AdminPassmord", result.getPassword());
        assertEquals(1, ReflectionTestUtils.getField(result, "id"));
        
        verify(repo, times(1)).save(adminToCreate);
    }
    
    @Test
    void testDeleteAdmin() {
        when(repo.existsById(1)).thenReturn(true);
        service.deleteAdmin(1);
        verify(repo, times(1)).deleteById(1);
    }
    
    @Test
    void testDeleteAdminNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.deleteAdmin(1);
        });
        assertEquals("Admin not found", exception.getMessage());
        verify(repo, never()).deleteById(anyInt());
    }
}

