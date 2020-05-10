package pl.zzpj.pharmacy.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zzpj.pharmacy.api.model.Client;
import pl.zzpj.pharmacy.api.model.Order;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE :order MEMBER OF c.orders")
    Optional<Client> findByOrder( @Param("order") Order order );

    @Query("SELECT c FROM Client c WHERE c.firstName =:firstName AND c.lastName =:lastName")
    List<Client> findByFirstAndLastName( @Param("firstName") String firstName, @Param("lastName") String lastName );

    Optional<Client> findById( Long id );
}
