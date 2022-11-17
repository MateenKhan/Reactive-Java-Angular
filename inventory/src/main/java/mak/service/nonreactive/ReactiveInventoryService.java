package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.pojo.Product;
import mak.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReactiveInventoryService {
    ProductRepository productRepository;

    @Transactional
    public Mono<Order> handleOrder(Order inputOrder) {
//        order.getProducts()
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
        return Flux.fromIterable(inputOrder.getProducts())
                .flatMap(inputProduct -> productRepository.findById(inputProduct.getProductId()))
                .flatMap(l->{
                    return productRepository.save(l);
                })
                .then(Mono.just(inputOrder.builder().setOrderStatus("SUCCESS").build()))
//                .flatMap(dbProduct -> {
//                    int inputQuantity = inputOrder.getProducts().stream()
//                            .filter(o -> o.getProductId().equals(dbProduct.getProductId()))
//                            .findAny().get()
//                            .getQuantity();
//                    if (dbProduct.getStock() >= inputQuantity) {
//                        dbProduct.setStock(dbProduct.getStock() - inputQuantity);
//                        return productRepository.save(dbProduct);
//                    } else {
//                        return Mono.error(new RuntimeException("Product is out of stock: " + dbProduct.getProductId()));
//                    }
//                })
//
//                .then();
    }

    @Transactional
    public Mono<Order> revertOrder(Order order) {
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
