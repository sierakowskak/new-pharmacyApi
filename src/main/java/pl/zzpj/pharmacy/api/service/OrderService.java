package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.OrderException;
import pl.zzpj.pharmacy.api.objectDTO.OrderDTO;
import pl.zzpj.pharmacy.api.objectDTO.mapper.OrderMapper;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orders;
    private OrderMapper mapper;

    @Autowired
    public OrderService(OrderRepository orders, OrderMapper mapper) {
        this.orders = orders;
        this.mapper = mapper;
    }

    public OrderDTO getOrder(long id) {
        return orders.findById(id)
                .map(ord -> mapper.toOrderDTO(ord))
                .orElseThrow(() -> new OrderException("Zamówienie o podanym id nie istnieje"));
    }

    public Boolean removeOrder(long id) {
        try {
            orders.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new OrderException("Zamówienie o podanym id nie istnieje");
        }
    }

    public List<OrderDTO> getAllOrders() {
        return orders.findAll()
                .stream()
                .map(ord -> mapper.toOrderDTO(ord))
                .collect(Collectors.toList());
    }

    public OrderDTO addOrder(OrderDTO order) {
        return mapper.toOrderDTO(orders.save(mapper.toOrder(order)));
    }

    public OrderDTO updateOrder(OrderDTO order) {
        return orders.findById(order.getId())
                .map(ord -> orders.save(mapper.toOrder(order)))
                .map(ord -> mapper.toOrderDTO(ord))
                .orElseThrow(() -> new OrderException("Klient o podanym id nie istnieje"));
    }
}
