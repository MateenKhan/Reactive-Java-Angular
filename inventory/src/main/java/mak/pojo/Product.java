package mak.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class Product {

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer productId;
    String productName;
    Integer stock, quantity;
}
