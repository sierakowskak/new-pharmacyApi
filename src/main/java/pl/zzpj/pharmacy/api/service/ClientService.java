package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.ClientException;
import pl.zzpj.pharmacy.api.objectDTO.ClientDTO;
import pl.zzpj.pharmacy.api.objectDTO.mapper.ClientMapper;
import pl.zzpj.pharmacy.api.repository.ClientRepository;
import pl.zzpj.pharmacy.api.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clients;
    private OrderRepository orders;
    private ClientMapper mapper;

    @Autowired
    public ClientService(ClientRepository clients, OrderRepository orders, ClientMapper mapper) {
        this.clients = clients;
        this.orders = orders;
        this.mapper = mapper;
    }

    public ClientDTO getClient(long id) {
        return clients.findById(id)
                .map(c -> mapper.toClientDTO(c))
                .orElseThrow(() -> new ClientException("Klient o podanym id nie istnieje"));
    }

    public Boolean removeClient(long id) {
        try {
            clients.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ClientException("Klient o podanym id nie istnieje");
        }
    }

    public List<ClientDTO> getAllClients() {
        return clients.findAll()
                .stream()
                .map(c -> mapper.toClientDTO(c))
                .collect(Collectors.toList());
    }

    public ClientDTO addClient(ClientDTO client) {
        return mapper.toClientDTO(clients.save(mapper.toClient(client)));
    }

    public ClientDTO updateClient(ClientDTO client) {
        return clients.findById(client.getId())
                .map(c -> clients.save(mapper.toClient(client)))
                .map(c -> mapper.toClientDTO(c))
                .orElseThrow(() -> new ClientException("Klient o podanym id nie istnieje"));
    }
}
