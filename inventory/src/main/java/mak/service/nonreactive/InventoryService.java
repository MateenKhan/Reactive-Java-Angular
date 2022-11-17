package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.repository.ProductRepository;
import mak.pojo.Order;
import mak.pojo.Product;
import mak.util.Utility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryService {

    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Order handleOrder(Order order) {
        Utility.sleep();
        order.getProducts()
                .forEach(products -> {
                    Product product  = productRepository.findById(products.getProductId())
                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + products.getProductId()));
                    if (product.getStock() >= products.getQuantity()) {
                        product.setStock(product.getStock() - products.getQuantity());
                        productRepository.save(product);
                    } else {
                        throw new RuntimeException("Product is out of stock: " + products.getProductId());
                    }
                });
        order.setOrderStatus("SUCCESS");
        return order;
    }

    public Order revertOrder(Order order) {
        Utility.sleep();
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
