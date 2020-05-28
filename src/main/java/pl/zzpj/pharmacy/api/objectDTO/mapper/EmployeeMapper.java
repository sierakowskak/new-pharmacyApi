package pl.zzpj.pharmacy.api.objectDTO.mapper;

import pl.zzpj.pharmacy.api.model.Employee;
import pl.zzpj.pharmacy.api.objectDTO.EmployeeDTO;

public class EmployeeMapper {

    public Employee toEmployee(EmployeeDTO EmployeeDTO) {
        return Employee.builder()
                // TO DO
                .build();
    }

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                // TO DO
                .build();
    }
}
