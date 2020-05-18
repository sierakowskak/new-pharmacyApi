package pl.zzpj.pharmacy.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String password;
    private final PasswordEncoderImpl passwordEncoder;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final UserDetailsService userDetailsService;
    private final Jwt jwt;

    @Autowired
    public SecurityConfiguration(@Value("${application.admin.password}") String password, PasswordEncoderImpl passwordEncoder,
                                 AuthEntryPointJwt unauthorizedHandler,
                                 @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                 Jwt jwt) {
        this.password = password;
        this.passwordEncoder = passwordEncoder;
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(this.password).roles("ADMIN");
//    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwt, this.userDetailsService);
    }

//    @Bean
//    public Jwt getJwt(){
//        return new Jwt();
//    }

//    @Bean
//    public AuthEntryPointJwt getAuthEntryPoint(){
//        return new AuthEntryPointJwt();
//    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()//TODO DO ZMIANY
                .antMatchers("/console/**").permitAll() //bez tego nie dziala consola h2
                .and()
                .httpBasic()
        .and().headers().frameOptions().disable(); //bez tego nie dziala consola h2
    }
}
