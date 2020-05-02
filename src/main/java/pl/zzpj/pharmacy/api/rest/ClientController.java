package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<Client> getClient(@RequestParam("id") long id) {
        if (clientService.getClient(id).isPresent())
            return new ResponseEntity<>(clientService.getClient(id).get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeClient(@PathVariable long id) {
        Optional<String> eMessage = clientService.removeClient(id);
        return eMessage.map(s -> new ResponseEntity<>(s, HttpStatus.BAD_REQUEST)) // Delete failed
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.OK)); // Delete succeeded
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        Optional<String> eMessage = clientService.addClient(client);
        return eMessage.map(s -> new ResponseEntity<>(s, HttpStatus.BAD_REQUEST)) // Add failed
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.OK)); // Add succeeded
    }

    @PutMapping()
    public ResponseEntity<String> updateClient(@RequestBody Client client){
        Optional<String> eMessage = clientService.updateClient(client);
        return eMessage.map(s -> new ResponseEntity<>(s, HttpStatus.BAD_REQUEST)) // Update failed
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.OK)); // Update succeeded
    }
}
