package pl.zzpj.pharmacy.api.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.model.Order;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clients;

    @Autowired
    private OrderRepository ordersRepo;

    @Test
    public void addClient() {
        Client client = Client.builder()
                .firstName("Piotr")
                .lastName("Ruc")
                .address("Tu")
                .build();
        clients.save(client);

        Optional<Client> result = clients.findById(2L);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getFirstName(), "Piotr");
        Assert.assertEquals(result.get().getLastName(), "Ruc");
        Assert.assertEquals(result.get().getAddress(), "Tu");
    }

    @Test
    public void findClientByOrder() {
        Set<Order> orders = new HashSet<>();
        Order order = Order.builder().id(1L).build();
        orders.add(order);
        Client client = Client.builder()
                .firstName("Piotr1")
                .lastName("Ruc1")
                .address("Tu1")
                .orders(orders)
                .build();
        order.setClient(client);
        clients.save(client);
        ordersRepo.save(order);

        Optional<Client> result = clients.findByOrder(order);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getFirstName(), "Piotr1");
        Assert.assertEquals(result.get().getLastName(), "Ruc1");
        Assert.assertEquals(result.get().getAddress(), "Tu1");
    }
}
