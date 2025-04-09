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

import edu.esiea.YelpEaBack.Controllers.CustomerController;
import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Services.CustomerService;

@WebMvcTest(CustomerController.class) 
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes=YelpEaBackApplication.class)
public class CustomerControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private CustomerService service;
    
    //Test get 
    @Test
    void testGetOne()throws Exception{
    	Customer mockedCustomer = new Customer("Customer1","CustomerPass");
    	when(service.getCustomerbyId(1)).thenReturn(mockedCustomer);
    	
    	mockMvc.perform(get("/customer/get/1")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.id").value(mockedCustomer.getId()))
    			.andExpect(jsonPath("$.username").value(mockedCustomer.getUsername()));
    }
    
    //Test get All
    @Test
    void testGetAll() throws Exception {
        Customer customer1 = new Customer("Customer1", "Pass1");
        Customer customer2 = new Customer("Customer2", "Pass2");
        
        when(service.getAllCustomer()).thenReturn(List.of(customer1, customer2));
        
        mockMvc.perform(get("/customer/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(customer1.getId()))
                .andExpect(jsonPath("$[0].username").value(customer1.getUsername()))
                .andExpect(jsonPath("$[1].id").value(customer2.getId()))
                .andExpect(jsonPath("$[1].username").value(customer2.getUsername()));
        
        verify(service, times(1)).getAllCustomer();
    }
    //Test create
    @Test
    void testCreateCustomer() throws Exception {
        Customer inputCustomer = new Customer("NewCustomer", "NewPass");
        Customer createdCustomer = new Customer("NewCustomer", "NewPass");
        
        when(service.createCustomer(any(Customer.class))).thenReturn(createdCustomer);
        
        mockMvc.perform(post("/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdCustomer.getId()))
                .andExpect(jsonPath("$.username").value(createdCustomer.getUsername()));
        
        verify(service, times(1)).createCustomer(any(Customer.class));
    }
    
    
    //Test Delete
    @Test
    void testDeleteCustomer() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/customer/delete/{id}", id))
               .andExpect(status().isNoContent());
        
        verify(service, times(1)).deleteCustomer(id);
    }

}
