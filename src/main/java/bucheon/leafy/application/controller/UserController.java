package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserService;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.jwt.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody SignInRequest signInRequest) {
        return userService.signIn(signInRequest);
    }

    @Operation(summary = "아이디 중복체크")
    @GetMapping("/check")
    public ResponseEntity<String> check(@RequestParam String email) {
        return userService.duplicationIdCheck(email);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
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

}
