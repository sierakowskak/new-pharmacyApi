package pl.zzpj.pharmacy.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConf  {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
