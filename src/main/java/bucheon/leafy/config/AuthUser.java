package bucheon.leafy.config;

import bucheon.leafy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class AuthUser implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 아이디별 권한 설정 여기서 실행하면됨!!

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getCustomerId();
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
