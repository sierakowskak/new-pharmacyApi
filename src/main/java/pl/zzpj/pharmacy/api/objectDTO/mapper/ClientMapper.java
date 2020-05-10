package pl.zzpj.pharmacy.api.objectDTO.mapper;

import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.objectDTO.ClientDTO;

public class ClientMapper {

    public static Client toClient(ClientDTO clientDTO) {
        return Client.builder()
                     .id(clientDTO.getId())
                     .address(clientDTO.getAddress())
                     .firstName(clientDTO.getFirstName())
                     .lastName(clientDTO.getLastName())
                     .build();
                // TO DO
                /*
                .id(clientDTO.getId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .address(clientDTO.getAddress())
                .orders(clientDTO.getOrders())
                .prescriptions(clientDTO.getPrescriptions())
                */
    }

    public static ClientDTO toClientDTO(Client client) {
        return ClientDTO.builder()
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .address(client.getAddress())
                        .id(client.getId()).build();
                // TO DO
                /*
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getLastName())
                .orders(client.getOrders())
                .prescriptions(client.getPrescriptions())
                 */
    }
}
