package mak.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.service.nonreactive.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryController {

    InventoryService inventoryService;

    @PostMapping
    public Order handleOrder(@RequestBody Order order) {
        return inventoryService.handleOrder(order);
    }

    @DeleteMapping
    public Order revertOrder(@RequestBody Order order) {
        return inventoryService.revertOrder(order);
    }
}
