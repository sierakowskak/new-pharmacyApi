package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @DeleteMapping("/{id}")
    public void removeOrder(@PathVariable long id) {
        orderService.removeOrder(id);
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping()
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @PutMapping()
    public void updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
    }
}
