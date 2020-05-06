package pl.zzpj.pharmacy.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.EmployeeException;
import pl.zzpj.pharmacy.api.model.Employee;
import pl.zzpj.pharmacy.api.objectDTO.EmployeeDTO;
import pl.zzpj.pharmacy.api.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employees;
    private ModelMapper mapper;

    @Autowired
    public EmployeeService(EmployeeRepository employees, ModelMapper mapper) {
        this.employees = employees;
        this.mapper = mapper;
    }

    public EmployeeDTO getEmployee(long id) {
        return employees.findById(id)
                .map(c -> mapper.map(c, EmployeeDTO.class))
                .orElseThrow(() -> new EmployeeException("Klient o podanym id nie istnieje"));
    }

    public Boolean removeEmployee(long id) {
        try {
            employees.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new EmployeeException("Klient o podanym id nie istnieje");
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employees.findAll()
                .parallelStream()
                .map(c -> mapper.map(c, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        return mapper.map(employees.save(mapper.map(employee, Employee.class)), EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        return employees.findById(employee.getId())
                .map(c -> employees.save(mapper.map(employee, Employee.class)))
                .map(c -> mapper.map(c, EmployeeDTO.class))
                .orElseThrow(() -> new EmployeeException("Klient o podanym id nie istnieje"));
    }
}
