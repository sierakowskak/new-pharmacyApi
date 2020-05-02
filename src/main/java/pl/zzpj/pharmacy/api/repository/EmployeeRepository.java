package pl.zzpj.pharmacy.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zzpj.pharmacy.api.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.firstName =:firstName AND e.lastName =:lastName")
    List<Employee> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Employee e SET e.firstName =:firstName, e.lastName =:lastName, e.login =:login, e.password =:password WHERE e.id =:id")
    void updateEmployee(@Param("id") long clientId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("login") String login, @Param("password") String password);

}
