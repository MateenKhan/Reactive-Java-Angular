package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.repository.ProductRepository;
import mak.pojo.Order;
import mak.pojo.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryService {
    ProductRepository productRepository;

    @Transactional
    public Order handleOrder(Order order) {
        order.getProducts()
                .forEach(l -> {
                    Product p = productRepository.findById(l.getProductId())
                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
                    if (p.getStock() >= l.getQuantity()) {
                        p.setStock(p.getStock() - l.getQuantity());
                        productRepository.save(p);
                    } else {
                        throw new RuntimeException("Product is out of stock: " + l.getProductId());
                    }
                });
        order.setOrderStatus("SUCCESS");
        return order;
    }

    @Transactional
    public Order revertOrder(Order order) {
        order.getProducts()
                .forEach(l -> {
                    Product p = productRepository.findById(l.getProductId())
                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
                    p.setStock(p.getStock() + l.getQuantity());
                    productRepository.save(p);
                });
        order.setOrderStatus("SUCCESS");
        return order;
    }
}
