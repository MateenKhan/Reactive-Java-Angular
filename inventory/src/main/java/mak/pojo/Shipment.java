package mak.pojo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(setterPrefix = "set")
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer shipmentId;
    String orderStatus,address;
    LocalDate shippingDate;
}
