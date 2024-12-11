package br.com.vilatoro.ordermanager.integrations;

import br.com.vilatoro.ordermanager.domain.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class ProductExternalService {

    private final RestTemplate restTemplate;

    public OrderEntity fetchOrderFromExternalServiceA() {
        String externalAUrl = "http://produto-service-external-a/api/orders";
        return restTemplate.getForObject(externalAUrl, OrderEntity.class);
    }

    public void sendOrderToExternalServiceB(OrderEntity order) {
        String externalBUrl = "http://produto-service-external-b/api/orders";
        restTemplate.postForObject(externalBUrl, order, Void.class);
    }
}