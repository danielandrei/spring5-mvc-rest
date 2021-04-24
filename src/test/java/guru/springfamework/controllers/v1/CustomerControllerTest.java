package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest implements RestControllerTest {
    public static final String NAME = "Jim";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("f1");
        customer1.setLastName("l1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("f2");
        customer2.setLastName("l2");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("f1");
        customer1.setLastName("l1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("f1")));
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("f1");
        customer.setLastName("l1");
        customer.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.firstName", equalTo("f1")))
            .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("f1");
        customer.setLastName("l1");
        customer.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(put("/api/v1/customers/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customer)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", equalTo("f1")))
            .andExpect(jsonPath("$.lastName", equalTo("l1")))
            .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    public void patchCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("f1");
        customer.setLastName("l1");
        customer.setCustomerUrl("/api/v1/customers/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(patch("/api/v1/customers/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customer)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", equalTo("f1")))
            .andExpect(jsonPath("$.lastName", equalTo("l1")))
            .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete("/api/v1/customers/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }
}
