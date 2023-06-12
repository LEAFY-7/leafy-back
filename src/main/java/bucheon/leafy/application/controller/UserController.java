package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.AuthoritiesUserService;
import bucheon.leafy.jwt.JwtFilter;
import bucheon.leafy.jwt.TokenProvider;
import bucheon.leafy.jwt.TokenResponse;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthoritiesUserService authoritiesUserService;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody SignInRequest signInRequest) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenResponse(jwt), httpHeaders, HttpStatus.OK);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return authoritiesUserService.signUp(signUpRequest);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/sign-out")
    public ResponseEntity signOut(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return ResponseEntity.ok("로그아웃 성공");
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "인증 테스트")
    @PostMapping("/auth/test")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL', 'ROLE_ADMIN')")
    public ResponseEntity test(HttpServletRequest request) {
        return ResponseEntity.badRequest().body(HttpStatus.OK);
    }

}
