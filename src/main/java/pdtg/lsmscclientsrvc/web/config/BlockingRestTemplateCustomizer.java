package pdtg.lsmscclientsrvc.web.config;

import lombok.Setter;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Diego T. 21-07-2022
 */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final Integer requestTimeout;
    private final Integer socketTimeout;
    private final Integer maxTotal;
    private final Integer maxPerRoute;

    public BlockingRestTemplateCustomizer(@Value("${pdtg.client.request-timeout}") Integer requestTimeout,
                                          @Value("${pdtg.client.socket-timeout}") Integer socketTimeout,
                                          @Value("${pdtg.client.max-total}") Integer maxTotal,
                                          @Value("${pdtg.client.max-per-route}") Integer maxPerRoute) {
        this.requestTimeout = requestTimeout;
        this.socketTimeout = socketTimeout;
        this.maxTotal = maxTotal;
        this.maxPerRoute = maxPerRoute;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(requestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient= HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory((httpClient));
    }

    @Override
    public void customize(RestTemplate restTemplate) {
            restTemplate.setRequestFactory(this.clientHttpRequestFactory());
        }

    }
