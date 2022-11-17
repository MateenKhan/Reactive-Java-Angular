package mak.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(setterPrefix = "set")
@Entity
@AllArgsConstructor
public class Order {

    public Order(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer orderId;
    String orderStatus,shippingAddress;
    @OneToMany(targetEntity=Product.class, fetch=FetchType.EAGER)
    List<Product> products;
    LocalDate shippingDate;
}
