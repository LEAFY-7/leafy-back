package bucheon.leafy.config.jwt;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.UserNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = null;
        try {
            // cookie 에서 JWT token 을 가져옵니다.
            token = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME)).findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        } catch (Exception ignored) {
        }
        if (token != null) {
            try {
                Authentication authentication = getUsernamePasswordAuthenticationToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken 를 만들어서 반환한다.
     * User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        String userName = JwtUtils.getUsername(token);
        if (userName != null) {

            User user = userRepository.findByUserEmail(userName)
                    .orElseThrow(UserNotFoundException::new);

            return new UsernamePasswordAuthenticationToken(
                    user, // principal
                    null,
                    new AuthUser(user).getAuthorities()
            );

        }
        return null; // 유저가 없으면 NULL
    }

}
