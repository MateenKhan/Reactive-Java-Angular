//package org.example.service.reactive;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.example.pojo.Order;
//import org.example.pojo.Product;
//import org.example.repo.ProductRepository;
//import org.example.util.Utility;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class ProductService {
//
//    ProductRepository productRepository;
//
//    public List<Product> getProducts() {
//        return productRepository.findAll();
//    }
//
//    public Order handleOrder(Order order) {
//        Utility.sleep();
//        order.getLineItems()
//                .forEach(l -> {
//                    Product p = productRepository.findById(l.getProductId())
//                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
//                    if (p.getStock() >= l.getQuantity()) {
//                        p.setStock(p.getStock() - l.getQuantity());
//                        productRepository.save(p);
//                    } else {
//                        throw new RuntimeException("Product is out of stock: " + l.getProductId());
//                    }
//                });
//        order.setOrderStatus("SUCCESS");
//        return order;
//    }
//
//    public Order revertOrder(Order order) {
//        Utility.sleep();
//        order.getLineItems()
//                .forEach(l -> {
//                    Product p = productRepository.findById(l.getProductId())
//                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
//                    p.setStock(p.getStock() + l.getQuantity());
//                    productRepository.save(p);
//                });
//        order.setOrderStatus("SUCCESS");
//        return order;
//    }
//}
