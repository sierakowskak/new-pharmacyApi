package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.pharmacy.api.objectDTO.OrderDTO;
import pl.zzpj.pharmacy.api.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeOrder(@PathVariable long id) {
        return new ResponseEntity<>(orderService.removeOrder(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.addOrder(orderDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.updateOrder(orderDTO), HttpStatus.OK);
    }
}
