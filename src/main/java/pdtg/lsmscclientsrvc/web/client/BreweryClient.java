package pdtg.lsmscclientsrvc.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pdtg.lsmscclientsrvc.web.model.BeerDto;

import java.util.UUID;

/**
 * Created by Diego T. 21-07-2022
 */

@Component
@ConfigurationProperties(value = "pdtg.msc", ignoreUnknownFields = false)
public class BreweryClient {
    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public void setApiHost(String apihost){
        this.apihost = apihost;
    }

    public BeerDto geetBeerById(UUID beerId){
        return restTemplate.getForObject(apihost+BEER_PATH_V1+beerId.toString(), BeerDto.class);
    }


}
