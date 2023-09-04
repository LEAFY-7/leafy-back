package bucheon.leafy.config;

import bucheon.leafy.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AuthUser implements UserDetails, OAuth2User {

    private final User user;
    private Map<String, Object> attributes;

    public AuthUser(User user) {
        this.user = user;
    }

    public AuthUser(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public User getUser(){
        return this.user;
    }

    public Long getUserId(){
        return this.user.getId();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail() == null ? user.getProviderId() : user.getEmail();
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

    @Override
    public String getName() {
        return attributes.get("sub").toString();
    }

}
