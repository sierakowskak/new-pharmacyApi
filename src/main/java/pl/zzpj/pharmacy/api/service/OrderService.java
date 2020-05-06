package pl.zzpj.pharmacy.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.OrderException;
import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.objectDTO.OrderDTO;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orders;
    private ModelMapper mapper;

    @Autowired
    public OrderService(OrderRepository orders, ModelMapper mapper) {
        this.orders = orders;
        this.mapper = mapper;
    }

    public OrderDTO getOrder(long id) {
        return orders.findById(id)
                .map(c -> mapper.map(c, OrderDTO.class))
                .orElseThrow(() -> new OrderException("Klient o podanym id nie istnieje"));
    }

    public Boolean removeOrder(long id) {
        try {
            orders.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new OrderException("Klient o podanym id nie istnieje");
        }
    }

    public List<OrderDTO> getAllOrders() {
        return orders.findAll()
                .stream()
                .map(c -> mapper.map(c, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO addOrder(OrderDTO order) {
        return mapper.map(orders.save(mapper.map(order, Order.class)), OrderDTO.class);
    }

    public OrderDTO updateOrder(OrderDTO order) {
        return orders.findById(order.getId())
                .map(c -> orders.save(mapper.map(order, Order.class)))
                .map(c -> mapper.map(c, OrderDTO.class))
                .orElseThrow(() -> new OrderException("Klient o podanym id nie istnieje"));
    }
}
