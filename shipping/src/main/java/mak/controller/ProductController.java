package mak.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.pojo.Product;
import mak.service.nonreactive.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    InventoryService inventoryService;

    @GetMapping
    public List<Product> getAllProducts() {
        return inventoryService.getProducts();
    }

    @PostMapping
    public Order processOrder(@RequestBody Order order) {
        return inventoryService.handleOrder(order);
    }

    @DeleteMapping
    public Order revertOrder(@RequestBody Order order) {
        return inventoryService.revertOrder(order);
    }
}
