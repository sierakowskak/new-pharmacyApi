package pl.zzpj.pharmacy.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.model.Order;
import pl.zzpj.pharmacy.api.model.Prescription;

import java.util.Optional;
import java.util.Set;

public interface ClientRepository extends JpaRepository <Client, Long> {

    @Query("SELECT c FROM Client c WHERE :order MEMBER OF c.orders")
    Optional<Client> findByOrder(@Param("order") Order order);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client c SET c.firstName =:firstName, c.lastName =:lastName, c.address =:address, c.orders =:orders, c.prescriptions =:prescriptions WHERE c.id =:id")
    void updateClient(@Param("id") long clientId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("address") String address, @Param("orders") Set<Order> orders, @Param("prescriptions") Set<Prescription> prescriptions);
}
