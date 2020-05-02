package pl.zzpj.pharmacy.api.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.model.Order;

import java.util.HashSet;
import java.util.List;
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

        List<Client> result = clients.findByFirstAndLastName("Piotr", "Ruc");
        Assert.assertFalse(result.isEmpty());
        Assert.assertTrue(result.stream().anyMatch(r -> r.getAddress().equals("Tu")));
    }

    @Test
    public void findClientByOrder() {
        Set<Order> orders = new HashSet<>();
        Order order = Order.builder().build();
        orders.add(order);
        Client client = Client.builder()
                .firstName("Niepiotr")
                .lastName("Nieruc")
                .address("Nietu")
                .orders(orders)
                .build();
        order.setClient(client);
        clients.save(client);
        ordersRepo.save(order);

        Optional<Client> result = clients.findByOrder(order);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getFirstName(), "Niepiotr");
        Assert.assertEquals(result.get().getLastName(), "Nieruc");
        Assert.assertEquals(result.get().getAddress(), "Nietu");
    }
}
