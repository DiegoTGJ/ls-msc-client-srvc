package pdtg.lsmscclientsrvc.web.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pdtg.lsmscclientsrvc.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void testGetCustomerById() {
    client.getCustomerById(UUID.randomUUID());
    }

    @Test
    void testSaveNewCustomer(){
        CustomerDto customerDto = CustomerDto.builder().name("some name").build();
        URI uri = client.saveNewCustomer(customerDto);
        assertNotNull(uri);
    }

    @Test
    void testUpdateCustomer(){
        CustomerDto customerDto = CustomerDto.builder().name("Some other name").build();
        client.updateCustomer(UUID.randomUUID() ,customerDto);
    }

    @Test
    void testDeleteCustomer(){
        client.deleteCustomer(UUID.randomUUID());
    }
}