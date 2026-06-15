package pl.cisowski.infrastructure.adapters.outgoing.auth;

import org.springframework.util.CollectionUtils;
import pl.cisowski.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user == null || CollectionUtils.isEmpty(user.getAuthorities()))
            return Collections.emptyList();

        return user.getAuthorities().stream()
                .filter(Objects::nonNull)
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    public Integer getId(){
        if(user == null)
            return null;
        return user.getId();
    }

    @Override
    public String getPassword() {
        if(user == null)
            return null;
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        if(user == null)
            return null;
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(user == null)
            return false;
        return user.isEnabled();
    }
}
