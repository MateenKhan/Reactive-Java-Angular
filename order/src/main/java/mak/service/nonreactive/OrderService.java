package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.repository.OrderRepository;
import mak.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    String inventoryServiceUrl;

    @Value("${inventory.service.url}")
    String shippingServiceUrl;

    public Order createOrder(Order order) {
        boolean success = true;
        Order savedOrder = orderRepository.save(order);
        Order inventoryResponse = null;
        try {
            inventoryResponse = restTemplate.postForObject(
                    inventoryServiceUrl, order, Order.class);
        } catch (Exception ex) {
            success = false;
        }
        Order shippingResponse = null;
        try {
            shippingResponse = restTemplate.postForObject(
                    shippingServiceUrl, order, Order.class);
        } catch (Exception ex) {
            success = false;
            HttpEntity<Order> deleteRequest = new HttpEntity<>(order);
            ResponseEntity<Order> deleteResponse = restTemplate.exchange(
                    inventoryServiceUrl, HttpMethod.DELETE, deleteRequest, Order.class);
        }
        if (success) {
            savedOrder.setOrderStatus("SUCCESS");
            savedOrder.setShippingDate(shippingResponse.getShippingDate());
        } else {
            savedOrder.setOrderStatus("FAILURE");
        }
        return orderRepository.save(savedOrder);
    }

}
