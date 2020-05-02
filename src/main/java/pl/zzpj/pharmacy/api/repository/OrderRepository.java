package pl.zzpj.pharmacy.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.model.MedicineOrder;
import pl.zzpj.pharmacy.api.model.Order;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT ord FROM Order ord WHERE ord.client =:client")
    List<Order> findByClient (@Param("client") Client client);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Order o SET o.client =:client, o.medicineOrders =:medicineOrders WHERE o.id =:id")
    void updateOrder(@Param("id") long clientId, @Param("client") Client client, @Param("medicineOrders") Set<MedicineOrder> medicineOrders);
}
