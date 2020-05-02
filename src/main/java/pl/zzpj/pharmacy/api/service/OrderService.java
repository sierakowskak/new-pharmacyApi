package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orders;

    @Autowired
    public OrderService(OrderRepository orders) {
        this.orders = orders;
    }

    public Optional<Order> getOrder(long id) {
        return orders.findById(id);
    }

    public void removeOrder(long id) {
        orders.deleteById(id);
    }

    public List<Order> getAllOrders() {
        return orders.findAll();
    }

    public void addOrder(Order order) {
        orders.save(order);
    }

    public void updateOrder(Order order){
        orders.updateOrder(
                order.getId(),
                order.getClient(),
                order.getMedicineOrders()
        );
    }
}
