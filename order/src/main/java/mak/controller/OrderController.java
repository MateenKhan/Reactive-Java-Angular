package mak.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.service.nonreactive.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {

    OrderService orderService;

    @PostMapping
    public Order handleOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

}
