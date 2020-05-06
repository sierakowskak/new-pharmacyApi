package pl.zzpj.pharmacy.api.objectDTO.mapper;

import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.objectDTO.OrderDTO;

public class OrderMapper {

    public Order toOrder(OrderDTO orderDTO) {
        return Order.builder()
                // TO DO
                .build();
    }

    public OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                // TO DO
                .build();
    }
}
