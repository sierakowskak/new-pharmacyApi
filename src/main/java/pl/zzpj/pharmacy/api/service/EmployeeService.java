package pl.zzpj.pharmacy.api.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zzpj.pharmacy.api.exception.EmployeeException;
import pl.zzpj.pharmacy.api.objectDTO.EmployeeDTO;
import pl.zzpj.pharmacy.api.objectDTO.LoginDTO;
import pl.zzpj.pharmacy.api.objectDTO.mapper.EmployeeMapper;
import pl.zzpj.pharmacy.api.repository.EmployeeRepository;
import pl.zzpj.pharmacy.api.security.Jwt;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private Jwt jwtUtils;
    private UserDetailsService userDetailsService;


    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, Jwt jwtUtils,
        @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    public EmployeeDTO getEmployee(long id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::toEmployeeDTO)
                .orElseThrow(() -> new EmployeeException("Pracownik o podanym id nie istnieje"));
    }

    public void removeEmployee(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeException("Pracownik o podanym id nie istnieje");
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        if (!employeeRepository.existsByLogin(employee.getLogin())) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            return EmployeeMapper.toEmployeeDTO(employeeRepository.save(EmployeeMapper.toEmployee(employee)));
        } else {
            throw new EmployeeException("Pracownik o podanym loginie już istnieje");
        }
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.findById(employee.getId())
                .map(e -> employeeRepository.save(
                        EmployeeMapper.toEmployee(employee)))
                .map(EmployeeMapper::toEmployeeDTO)
                .orElseThrow(() -> new EmployeeException("Klient o podanym id nie istnieje"));
    }

    public Pair<EmployeeDTO, String> loginEmployee(LoginDTO loginDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getLogin());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, loginDTO.getPassword(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        EmployeeDTO employeeDTO = (EmployeeDTO) authentication.getPrincipal();

        return Pair.of(employeeDTO, jwtToken);
    }
}
