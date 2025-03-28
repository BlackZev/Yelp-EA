package edu.esiea.YelpEaBack;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.esiea.YelpEaBack.YelpEaBackApplication;
import edu.esiea.YelpEaBack.Controllers.CustomerController;
import edu.esiea.YelpEaBack.Entities.Customer;
import edu.esiea.YelpEaBack.Services.CustomerService;

@WebMvcTest(CustomerController.class) 
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes=YelpEaBackApplication.class)
public class CustomerControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
	private CustomerService service;
    
    @Test
    void testGetOne()throws Exception{
    	Customer mockedCustomer = new Customer(1,"Customer1","CustomerPass");
    	when(service.getCustomerbyId(1)).thenReturn(mockedCustomer);
    	
    	mockMvc.perform(get("/customer/1")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.id").value(mockedCustomer.getID()))
    			.andExpect(jsonPath("$.username").value(mockedCustomer.getUsername()));
    }
    

}
