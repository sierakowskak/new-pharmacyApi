package pl.zzpj.pharmacy.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.ClientException;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.objectDTO.ClientDTO;
import pl.zzpj.pharmacy.api.repository.ClientRepository;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clients;
    private OrderRepository orders;
    private ModelMapper mapper;

    @Autowired
    public ClientService(ClientRepository clients, OrderRepository orders) {
        this.clients = clients;
        this.orders = orders;
        this.mapper = new ModelMapper();
    }

    public ClientDTO getClient(long id) {
        return clients.findById(id)
                .map(c -> mapper.map(c, ClientDTO.class))
                .orElseThrow(() -> new ClientException("Klient o podanym id nie istnieje"));
    }

    public Boolean removeClient(long id) {
        try {
            orders.deleteAll(orders.findByClient(clients.findById(id).get()));
            clients.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ClientException("Klient o podanym id nie istnieje");
        }
    }

    public List<ClientDTO> getAllClients() {
        return clients.findAll()
                .parallelStream()
                .map(c -> mapper.map(c, ClientDTO.class))
                .collect(Collectors.toList());
    }

    public ClientDTO addClient(ClientDTO client) {
        return mapper.map(clients.save(mapper.map(client, Client.class)), ClientDTO.class);
    }

    public ClientDTO updateClient(ClientDTO client) {
        return clients.findById(client.getId())
                .map(c -> clients.save(mapper.map(client, Client.class)))
                .map(c -> mapper.map(c, ClientDTO.class))
                .orElseThrow(() -> new ClientException("Klient o podanym id nie istnieje"));
    }
}
