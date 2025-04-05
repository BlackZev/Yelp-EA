package edu.esiea.YelpEaBack;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import edu.esiea.YelpEaBack.Controllers.RestaurantController;
import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Enum.TypeRestauEnum;
import edu.esiea.YelpEaBack.Services.RestaurantService;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private RestaurantService service;
    
 // Test create
    
    @Test
    void testCreateRestaurant() throws Exception {
        Restaurant inputRestaurant = new Restaurant(1,"Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.Chinois);
        // Le service retourne le restaurant créé avec un id attribué
        Restaurant createdRestaurant = new Restaurant(1,"Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.Chinois);
        
        when(service.create(any(Restaurant.class))).thenReturn(createdRestaurant);
        
        mockMvc.perform(post("/Restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputRestaurant)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(createdRestaurant.getID()))
            .andExpect(jsonPath("$.name").value(createdRestaurant.getName()));
        
        verify(service, times(1)).create(any(Restaurant.class));
    }
    
 // Test pour getAll
    @Test
    void testGetAllRestaurants() throws Exception {
        Restaurant restaurant1 = new Restaurant(1,"Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.Chinois);
        Restaurant restaurant2 = new Restaurant(2,"Resto2", "Address2", "Phone2", "Desc2", TypeRestauEnum.FastFood);

        
        when(service.getAll()).thenReturn(List.of(restaurant1, restaurant2));
        
        mockMvc.perform(get("/Restaurant/all")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(restaurant1.getID()))
            .andExpect(jsonPath("$[0].name").value(restaurant1.getName()))
            .andExpect(jsonPath("$[1].id").value(restaurant2.getID()))
            .andExpect(jsonPath("$[1].name").value(restaurant2.getName()));
        
        verify(service, times(1)).getAll();
    }

   //test get(id) 
    @Test
    void testGetRestaurant() throws Exception {
        Restaurant mockedRestaurant = new Restaurant(1,"Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.Japonais);

        
        when(service.get(1)).thenReturn(mockedRestaurant);
        
        mockMvc.perform(get("/Restaurant/get/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(mockedRestaurant.getID()))
            .andExpect(jsonPath("$.name").value(mockedRestaurant.getName()));
        
        verify(service, times(1)).get(1);
    }
    
    
    //test Update
    @Test
    void testUpdateRestaurant() throws Exception {
        int id = 1;
        Restaurant inputRestaurant = new Restaurant(1,"UpdatedResto", "UpdatedAddress", "UpdatedPhone", "UpdatedDesc", TypeRestauEnum.Italien);
        Restaurant updatedRestaurant = new Restaurant(1,"UpdatedResto", "UpdatedAddress", "UpdatedPhone", "UpdatedDesc", TypeRestauEnum.Italien);

        
        when(service.update(eq(id), any(Restaurant.class))).thenReturn(updatedRestaurant);
        
        mockMvc.perform(put("/Restaurant/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputRestaurant)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(updatedRestaurant.getID()))
            .andExpect(jsonPath("$.name").value(updatedRestaurant.getName()));
        
        verify(service, times(1)).update(eq(id), any(Restaurant.class));
    }
    
    
    
    // Test Delete
    @Test
    void testDeleteRestaurant() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/Restaurant/delete/{id}", id))
            .andExpect(status().isNoContent());
        
        verify(service, times(1)).delete(id);
    }
}
