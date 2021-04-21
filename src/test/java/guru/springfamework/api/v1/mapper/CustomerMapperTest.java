package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import javax.persistence.Id;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public final Long ID = 2L;
    public final String FIRST_NAME = "John";
    public final String LAST_NAME = "Doe";

    CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        Customer customer = Customer.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);

        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}