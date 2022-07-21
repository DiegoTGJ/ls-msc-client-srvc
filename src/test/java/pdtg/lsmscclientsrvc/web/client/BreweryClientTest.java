package pdtg.lsmscclientsrvc.web.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pdtg.lsmscclientsrvc.web.model.BeerDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void geetBeerById() {
        BeerDto beerDto = client.geetBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }
}