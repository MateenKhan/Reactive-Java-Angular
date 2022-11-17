package mak.pojo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(setterPrefix = "set")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer orderId;
    String orderStatus,shippingAddress;
    @OneToMany(targetEntity=Product.class, fetch=FetchType.EAGER)
    List<Product> products;
    LocalDate shippingDate;
}
