package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientRepository clients;

    @Autowired
    public ClientController(ClientRepository clients) {
        this.clients = clients;
    }

    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable long id) {
        return clients.findById(id);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable long id) {
        clients.deleteById(id);
    }

    @GetMapping()
    public List<Client> getAllClients() {
        return clients.findAll();
    }

    @PostMapping()
    public void addClient(@RequestBody Client client) {
        clients.save(client);
    }

    @PutMapping()
    public void updateClient(@RequestBody Client client){
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
