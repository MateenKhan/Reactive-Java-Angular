package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Shipment;
import mak.repository.ShipmentRepository;
import mak.pojo.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentService {
    ShipmentRepository shipmentRepository;

    @Transactional
    public Order handleOrder(Order order) {
        LocalDate shippingDate = null;
        if (LocalTime.now().isAfter(LocalTime.parse("10:00"))
                && LocalTime.now().isBefore(LocalTime.parse("18:00"))) {
            shippingDate = LocalDate.now().plusDays(1);
        } else {
            throw new RuntimeException("The current time is off the limits to place order.");
        }
        shipmentRepository.save(Shipment.builder()
                .setAddress(order.getShippingAddress())
                .setShippingDate(shippingDate).build());
        order.setShippingDate(shippingDate);
        order.setOrderStatus("SUCCESS");
        return order;
    }

}
