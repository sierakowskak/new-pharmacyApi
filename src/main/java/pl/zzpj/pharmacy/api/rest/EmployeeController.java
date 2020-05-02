package pl.zzpj.pharmacy.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.pharmacy.api.model.Employee;
import pl.zzpj.pharmacy.api.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void removeEmployee(@PathVariable long id) {
        employeeService.removeEmployee(id);
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping()
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }
}
