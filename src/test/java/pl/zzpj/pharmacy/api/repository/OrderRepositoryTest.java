package pl.zzpj.pharmacy.api.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.zzpj.pharmacy.api.model.Order;

import java.util.Optional;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepo;

    @Test
    public void addOrder() {
        Order order = Order.builder().build();
        orderRepo.save(order);

        Optional<Order> result = orderRepo.findById(1L);
        Assert.assertTrue(result.isPresent());
    }
}
