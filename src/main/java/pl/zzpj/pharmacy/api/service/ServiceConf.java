package pl.zzpj.pharmacy.api.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zzpj.pharmacy.api.objectDTO.mapper.ClientMapper;
import pl.zzpj.pharmacy.api.objectDTO.mapper.EmployeeMapper;
import pl.zzpj.pharmacy.api.objectDTO.mapper.OrderMapper;

@Configuration
public class ServiceConf  {

    @Bean
    public ClientMapper getClientMapper() {
        return new ClientMapper();
    }

    @Bean
    public OrderMapper getOrderMapper() {
        return new OrderMapper();
    }

    @Bean
    public EmployeeMapper getEmployeeMapper() {
        return new EmployeeMapper();
    }
}
