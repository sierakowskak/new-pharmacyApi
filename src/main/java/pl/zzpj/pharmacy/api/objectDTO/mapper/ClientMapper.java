package pl.zzpj.pharmacy.api.objectDTO.mapper;

import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.objectDTO.ClientDTO;

public class ClientMapper {

    public static Client toClient(ClientDTO clientDTO) { //, Set<Order> orders) {
        return Client.builder()
                     .id(clientDTO.getId())
                     .address(clientDTO.getAddress())
                     .firstName(clientDTO.getFirstName())
                     .lastName(clientDTO.getLastName())
                     //.orders(orders)
                     //.prescriptions(clientDTO.getPrescriptions())
                     .build();
    }

    public static ClientDTO toClientDTO(Client client) {
        /*Set<OrderDTO> orders = client.getOrders()
                .stream()
                .map(order -> OrderMapper.toOrderDTO(order))
                .collect(Collectors.toSet());*/
        return ClientDTO.builder()
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .address(client.getAddress())
                        .id(client.getId())
                        //.orders(orders)
                        .build();
    }
}
