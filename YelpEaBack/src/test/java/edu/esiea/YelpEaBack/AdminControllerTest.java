package edu.esiea.YelpEaBack;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.YelpEaBack.Controllers.AdminController;
import edu.esiea.YelpEaBack.Entities.Admin;
import edu.esiea.YelpEaBack.Services.AdminService;

@WebMvcTest(AdminController.class) 
public class AdminControllerTest {

	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private AdminService service;
    
    //Test create
    @Test
    void testCreateAdmin() throws Exception {
    	Admin inputAdmin = new Admin(1, "NewAdmin", "NewPass");
    	Admin createdAdmin = new Admin(1, "NewAdmin", "NewPass");
        
        when(service.createAdmin(any(Admin.class))).thenReturn(createdAdmin);
        
        mockMvc.perform(post("/paneladmin/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputAdmin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdAdmin.getID()))
                .andExpect(jsonPath("$.username").value(createdAdmin.getUsername()));
        
        verify(service, times(1)).createAdmin(any(Admin.class));
    }
    
    //Test Delete
    @Test
    void testDeleteAdmin() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/paneladmin/delete/{id}", id))
               .andExpect(status().isNoContent());
        
        verify(service, times(1)).deleteAdmin(id);
    }
    
}
