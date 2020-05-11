package pl.zzpj.pharmacy.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.zzpj.pharmacy.api.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query("SELECT e FROM Employee e WHERE e.firstName =:firstName AND e.lastName =:lastName")
//    List<Employee> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Boolean existsByLogin(String login);
}
