package bucheon.leafy.config;

import bucheon.leafy.application.service.Oauth2UserService;
import bucheon.leafy.jwt.JwtAccessDeniedHandler;
import bucheon.leafy.jwt.JwtAuthenticationEntryPoint;
import bucheon.leafy.jwt.JwtSecurityConfig;
import bucheon.leafy.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final Oauth2UserService oauth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/static/image/**", "/static/css/**", "/static/js/**", "/image/**").permitAll()
                .antMatchers("/login/oauth2/**").permitAll()
                .antMatchers("/user/oauth-login-success").permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/api/v1/users/sign**").permitAll()
                .antMatchers("/api/v1/users/check/**").permitAll()
                .antMatchers("/api/v1/users/email").permitAll()
                .antMatchers("/api/v1/users//email-send").permitAll()
                .antMatchers("/api/v1/users/email-confirm").permitAll()
                .antMatchers("/api/v1/users//temporary-password").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .antMatchers("/api/v1/search/**").permitAll()
                .antMatchers("/api/v1/feeds/{feedId}").permitAll()
                .antMatchers("/api/v1/main").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/notice").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/notice/{noticeId}").permitAll()
                .antMatchers("/api/v1/notice/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .apply(new JwtSecurityConfig(tokenProvider))

                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauth2UserService);

        return http.build();
    }

}