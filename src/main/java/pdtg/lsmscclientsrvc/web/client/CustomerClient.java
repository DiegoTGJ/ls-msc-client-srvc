package pdtg.lsmscclientsrvc.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pdtg.lsmscclientsrvc.web.model.BeerDto;
import pdtg.lsmscclientsrvc.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

/**
 * Created by Diego T. 21-07-2022
 */

@Component
@ConfigurationProperties(value = "pdtg.msc", ignoreUnknownFields = false)
public class CustomerClient {
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public void setApiHost(String apihost){
        this.apihost = apihost;
    }

    public CustomerDto getCustomerById(UUID beerId){
        return restTemplate.getForObject(apihost+CUSTOMER_PATH_V1+beerId,CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost+CUSTOMER_PATH_V1,customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        restTemplate.put(apihost+CUSTOMER_PATH_V1+customerId,customerDto);
    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apihost+CUSTOMER_PATH_V1+customerId);
    }
}
