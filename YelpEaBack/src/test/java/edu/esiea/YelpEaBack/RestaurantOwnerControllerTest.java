package edu.esiea.YelpEaBack;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.YelpEaBack.Controllers.RestaurantOwnerController;
import edu.esiea.YelpEaBack.Entities.RestaurantOwner;
import edu.esiea.YelpEaBack.Services.RestaurantOwnerService;

@WebMvcTest(RestaurantOwnerController.class)
public class RestaurantOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RestaurantOwnerService service;
    
    
    //Test Create
    @Test
    void testCreateRestaurantOwner() throws Exception {
        RestaurantOwner inputOwner = new RestaurantOwner("owner1", "pass1");
        RestaurantOwner createdOwner = new RestaurantOwner("owner1", "pass1");

        when(service.create(any(RestaurantOwner.class))).thenReturn(createdOwner);

        mockMvc.perform(post("/restaurantOwner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputOwner)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(createdOwner.getId()))
            .andExpect(jsonPath("$.username").value(createdOwner.getUsername()));

        verify(service, times(1)).create(any(RestaurantOwner.class));
    }
    
    
    //Test getAll
    @Test
    void testGetAllRestaurantOwners() throws Exception {
        RestaurantOwner owner1 = new RestaurantOwner("owner1", "pass1");
        RestaurantOwner owner2 = new RestaurantOwner("owner2", "pass2");

        when(service.getAll()).thenReturn(List.of(owner1, owner2));

        mockMvc.perform(get("/restaurantOwner/all")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(owner1.getId()))
            .andExpect(jsonPath("$[0].username").value(owner1.getUsername()))
            .andExpect(jsonPath("$[1].id").value(owner2.getId()))
            .andExpect(jsonPath("$[1].username").value(owner2.getUsername()));

        verify(service, times(1)).getAll();
    }
    
    
    // Test Delete
    @Test
    void testDeleteRestaurantOwner() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/restaurantOwner/delete/{id}", id))
            .andExpect(status().isNoContent());

        verify(service, times(1)).delete(id);
    }

}
