package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.model.Employee;
import pl.zzpj.pharmacy.api.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employees;

    @Autowired
    public EmployeeService(EmployeeRepository employees) {
        this.employees = employees;
    }

    public Optional<Employee> getEmployee(long id) {
        return employees.findById(id);
    }

    public void removeEmployee(long id) {
        employees.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return employees.findAll();
    }

    public void addEmployee(Employee employee) {
        employees.save(employee);
    }

    public void updateEmployee(Employee employee){
        employees.updateEmployee(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getLogin(),
                employee.getPassword()
        );
    }
}
