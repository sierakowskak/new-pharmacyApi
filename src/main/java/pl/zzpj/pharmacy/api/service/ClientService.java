package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.repository.ClientRepository;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
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

    public Optional<String> removeClient(long id) {
        Optional<Client> client = clients.findById(id);
        try {
            orders.deleteAll(orders.findByClient(client.get()));
            clients.delete(client.get());
            return Optional.empty();
        } catch (Exception e) {
            return Optional.ofNullable(e.getMessage());
        }
    }

    public List<Client> getAllClients() {
        return clients.findAll();
    }

    public Optional<String> addClient(Client client) {
        try {
            clients.save(client);
            return Optional.empty();
        } catch (Exception e) {
            return Optional.ofNullable(e.getMessage());
        }
    }

    public Optional<String> updateClient(Client client){
        try {
            clients.updateClient(
                    client.getId(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getAddress(),
                    client.getOrders(),
                    client.getPrescriptions()
            );
            return Optional.empty();
        } catch (Exception e) {
            return Optional.ofNullable(e.getMessage());
        }
    }
}
