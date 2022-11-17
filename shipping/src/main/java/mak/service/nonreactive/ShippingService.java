package mak.service.nonreactive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mak.pojo.Order;
import mak.pojo.Shipment;
import mak.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShippingService {

    ShipmentRepository shipmentRepository;

    public Order handleOrder(Order order) {
        LocalDate shippingDate = null;
        if (LocalTime.now().isAfter(LocalTime.parse("10:00"))
                && LocalTime.now().isBefore(LocalTime.parse("18:00"))) {
            shippingDate = LocalDate.now().plusDays(1);
        } else {
            throw new RuntimeException("The current time is off the limits to place order.");
        }
        Shipment shipment = Shipment.builder()
                .setAddress(order.getShippingAddress())
                .setShippingDate(shippingDate)
                .build();
        shipmentRepository.save(shipment);

        order.setShippingDate(shippingDate);
        order.setOrderStatus("SUCCESS");
        return order;
    }
}
