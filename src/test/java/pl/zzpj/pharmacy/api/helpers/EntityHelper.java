package pl.zzpj.pharmacy.api.helpers;

import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.model.Medicine;
import pl.zzpj.pharmacy.api.model.MedicineOrder;
import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.objectDTO.ClientDTO;
import pl.zzpj.pharmacy.api.objectDTO.OrderDTO;

public class EntityHelper {

    public static ClientDTO prepareClientDTO(Long id) {
        return new ClientDTO().builder()
                              .id(id)
                              .address("jakis")
                              .firstName("Marek")
                              .lastName("Mostowiak")
                              .build();
    }

    public static Client prepareClient(Long id) {
        return new Client().builder()
                           .id(id)
                           .address("jakis")
                           .firstName("Marek")
                           .lastName("Mostowiak")
                           .build();
    }

    public static Order prepareOrder(Long id, Client client) {
        return new Order().builder()
                          .client(client)
                          .id(id)
                          .build();
    }

    public static OrderDTO prepareOrderDTO(Long id, ClientDTO client) {
        return new OrderDTO().builder()
                             .client(client)
                             .id(id)
                             .build();
    }

    public static Medicine prepareMedicine(Long id) {
        Medicine medicine = new Medicine();
        medicine.setId(id);
        medicine.set_prescript(false);
        medicine.setName("Acodin");
        return medicine;
    }

    public static MedicineOrder prepareMedicineOrder(Medicine medicine, Order order) {
        MedicineOrder medicineOrder = new MedicineOrder();
        medicineOrder.setAmount(4);
        medicineOrder.setMedicine(medicine);
        medicineOrder.setOrder(order);
        return medicineOrder;
    }
}
