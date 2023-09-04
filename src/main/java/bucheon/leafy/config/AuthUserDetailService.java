package bucheon.leafy.config;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oauthUser = userRepository.findByProviderId(username);
        if (oauthUser.isPresent()){
            return new AuthUser(oauthUser.get());
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);

        return new AuthUser(user);
    }

}
