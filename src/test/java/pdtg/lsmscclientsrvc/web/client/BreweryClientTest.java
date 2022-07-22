package pdtg.lsmscclientsrvc.web.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pdtg.lsmscclientsrvc.web.model.BeerDto;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() {
        //given
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();

        URI uri = client.saveNewBeer(beerDto);

        assertNotNull(uri);
    }

    @Test
    void updateBeer() {
        //given
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();

        client.updateBeer(UUID.randomUUID(),beerDto);
    }

    @Test
    void deleteBeer(){
        client.deleteBeer(UUID.randomUUID());
    }
}