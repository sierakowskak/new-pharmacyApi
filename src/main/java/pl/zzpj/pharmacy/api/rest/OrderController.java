package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orders;

    @Autowired
    public OrderController(OrderRepository orders) {
        this.orders = orders;
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable long id) {
        return orders.findById(id);
    }

    @DeleteMapping("/{id}")
    public void removeOrder(@PathVariable long id) {
        orders.deleteById(id);
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        return orders.findAll();
    }

    @PostMapping()
    public void addOrder(@RequestBody Order order) {
        orders.save(order);
    }

    @PutMapping()
    public void updateOrder(@RequestBody Order order){
        orders.updateOrder(
                order.getId(),
                order.getClient(),
                order.getMedicineOrders()
        );
    }
}
