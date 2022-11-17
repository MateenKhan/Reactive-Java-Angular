package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {

    OrderRepository orderRepository;
    InventoryService inventoryService;
    ShippingService shippingService;

    public Order createOrder(Order order) {
        boolean success = true;
        Order savedOrder = orderRepository.save(order);
        try {
            inventoryService.handleOrder(order);
        } catch (Exception ex) {
            success = false;
        }
        Order shippingResponse = null;
        try {
            shippingResponse = shippingService.handleOrder(order);
        } catch (Exception ex) {
            success = false;
            inventoryService.revertOrder(order);
        }
        if (success) {
            savedOrder.setOrderStatus("SUCCESS");
            savedOrder.setShippingDate(shippingResponse.getShippingDate());
        } else {
            savedOrder.setOrderStatus("FAILURE");
        }
        return orderRepository.save(savedOrder);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
