package mak.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.service.nonreactive.InventoryService;
import mak.service.nonreactive.ReactiveInventoryService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive/inventory")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReactiveInventoryController {

    ReactiveInventoryService inventoryService;

    @PostMapping
    public Mono<Order> handleOrder(@RequestBody Order order) {
        return inventoryService.handleOrder(order);
    }

    @DeleteMapping
    public Mono<Order> revertOrder(@RequestBody Order order) {
        return inventoryService.revertOrder(order);
    }
}
