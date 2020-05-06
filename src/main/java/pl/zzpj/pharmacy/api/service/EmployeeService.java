package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.EmployeeException;
import pl.zzpj.pharmacy.api.objectDTO.EmployeeDTO;
import pl.zzpj.pharmacy.api.objectDTO.mapper.EmployeeMapper;
import pl.zzpj.pharmacy.api.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employees;
    private EmployeeMapper mapper;

    @Autowired
    public EmployeeService(EmployeeRepository employees, EmployeeMapper mapper) {
        this.employees = employees;
        this.mapper = mapper;
    }

    public EmployeeDTO getEmployee(long id) {
        return employees.findById(id)
                .map(e -> mapper.toEmployeeDTO(e))
                .orElseThrow(() -> new EmployeeException("Pracownik o podanym id nie istnieje"));
    }

    public Boolean removeEmployee(long id) {
        try {
            employees.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new EmployeeException("Pracownik o podanym id nie istnieje");
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employees.findAll()
                .parallelStream()
                .map(e -> mapper.toEmployeeDTO(e))
                .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        return mapper.toEmployeeDTO(employees.save(mapper.toEmployee(employee)));
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        return employees.findById(employee.getId())
                .map(c -> employees.save(mapper.toEmployee(employee)))
                .map(c -> mapper.toEmployeeDTO(c))
                .orElseThrow(() -> new EmployeeException("Klient o podanym id nie istnieje"));
    }
}
