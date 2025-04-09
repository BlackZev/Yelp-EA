package edu.esiea.YelpEaBack;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.YelpEaBack.Controllers.RatingController;
import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Entities.Rating;
import edu.esiea.YelpEaBack.Entities.Restaurant;
import edu.esiea.YelpEaBack.Enum.TypeRestauEnum;
import edu.esiea.YelpEaBack.Services.RatingService;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private RatingService service;

	// Test create
    @Test
    void testCreateRating() throws Exception {
        Customer testCustomer = new Customer("cust1", "pass1");
        Restaurant testRestaurant = new Restaurant("Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.FastFood);

        Rating inputRating = new Rating(testCustomer, testRestaurant, 4);
        Rating createdRating = new Rating(testCustomer, testRestaurant, 4);
        when(service.create(any(Rating.class))).thenReturn(createdRating);
        
        mockMvc.perform(post("/Rating")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputRating)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.value").value(createdRating.getValue()));
        
        verify(service, times(1)).create(any(Rating.class));
    }
    
	// Test update
    @Test
    void testUpdateRating() throws Exception {
        int ratingId = 1;
        Customer testCustomer = new Customer("cust1", "pass1");
        Restaurant testRestaurant = new Restaurant("Resto1", "Address1", "Phone1", "Desc1", TypeRestauEnum.FastFood);
        

        Rating inputRating = new Rating(testCustomer, testRestaurant, 5);
        

        Rating updatedRating = new Rating(testCustomer, testRestaurant, 5);
        
        when(service.update(eq(ratingId), any(Rating.class))).thenReturn(updatedRating);
        
        mockMvc.perform(put("/Rating/{id}", ratingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputRating)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.value").value(updatedRating.getValue()));
        
        verify(service, times(1)).update(eq(ratingId), any(Rating.class));
    }
    
	// Test delete
    @Test
    void testDeleteRating() throws Exception {
        int ratingId = 1;
        
        mockMvc.perform(delete("/Rating/{id}", ratingId))
            .andExpect(status().isNoContent());
        
        verify(service, times(1)).delete(ratingId);
    }
}
