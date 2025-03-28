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

