package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable long id) {
        return clientService.getClient(id);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable long id) {
        clientService.removeClient(id);
    }

    @GetMapping()
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping()
    public void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @PutMapping()
    public void updateClient(@RequestBody Client client){
        clientService.updateClient(client);
    }
}
