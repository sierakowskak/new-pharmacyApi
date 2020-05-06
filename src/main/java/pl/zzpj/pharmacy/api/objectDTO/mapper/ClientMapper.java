package pl.zzpj.pharmacy.api.objectDTO.mapper;

import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.objectDTO.ClientDTO;

public class ClientMapper {

    public Client toClient(ClientDTO clientDTO) {
        return Client.builder()
                // TO DO
                /*
                .id(clientDTO.getId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .address(clientDTO.getAddress())
                .orders(clientDTO.getOrders())
                .prescriptions(clientDTO.getPrescriptions())
                */
                .build();
    }

    public ClientDTO toClientDTO(Client client) {
        return ClientDTO.builder()
                // TO DO
                /*
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getLastName())
                .orders(client.getOrders())
                .prescriptions(client.getPrescriptions())
                 */
                .build();
    }
}
