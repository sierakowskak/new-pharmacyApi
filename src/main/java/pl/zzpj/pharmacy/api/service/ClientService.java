package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.repository.ClientRepository;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ClientService {

    private ClientRepository clients;
    private OrderRepository orders;

    @Autowired
    public ClientService(ClientRepository clients, OrderRepository orders) {
        this.clients = clients;
        this.orders = orders;
    }

    public Optional<Client> getClient(long id) {
        return clients.findById(id);
    }

    public void removeClient(long id) {
        clients.deleteById(id);
    }

    public List<Client> getAllClients() {
        return clients.findAll();
    }

    public void addClient(Client client) {
        clients.save(client);
    }

    public void updateClient(Client client){
        clients.updateClient(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getAddress(),
                client.getOrders(),
                client.getPrescriptions()
        );
    }
}
