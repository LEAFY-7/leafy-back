package bucheon.leafy.config;

import bucheon.leafy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class AuthUser implements UserDetails {

    private final User user;

    public User getUser(){
        return this.user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getIsDelete();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsDelete();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getIsDelete();
    }

    @Override
    public boolean isEnabled() {
        return !user.getIsDelete();
    }
}
