package pl.zzpj.pharmacy.api.objectDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.zzpj.pharmacy.api.model.Employee;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class EmployeeDetails implements UserDetails {

    @Getter
    private Long id;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    private String login;
    private String password;

    public EmployeeDetails (Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.login = employee.getLogin();
        this.password = employee.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> h = new HashSet<>();
        h.add(new SimpleGrantedAuthority("USER"));
        return h;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
