package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserImageService;
import bucheon.leafy.application.service.UserService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.jwt.JwtFilter;
import bucheon.leafy.jwt.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserImageService userImageService;

    @Operation(summary = "Get Me")
    @GetMapping
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = userService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody SignInRequest signInRequest) {
        TokenResponse tokenResponse = userService.signIn(signInRequest);
        insertTokenInHeader(tokenResponse);
        return ResponseEntity.status(200).body(tokenResponse);
    }

    @Operation(summary = "아이디 중복체크")
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public void check(@RequestParam String email) {
        userService.duplicationIdCheck(email);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        Long userId = userService.signUp(signUpRequest);
        SignInRequest signInRequest = SignInRequest.of(signUpRequest);
        
        TokenResponse tokenResponse = userService.signIn(signInRequest);
        tokenResponse.addUserId(userId);
        insertTokenInHeader(tokenResponse);
        return ResponseEntity.status(200).body(tokenResponse);
    }

    @Operation(summary = "회원 이미지 등록")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/image")
    public void createImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                              MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.createUserImage(userId, "안녕");
    }

    @Operation(summary = "배경 이미지 등록")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/background-image")
    public void createBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                        MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.createUserBackgroundImage(userId, file);
    }

    @Operation(summary = "회원 이미지 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/image")
    public void updateImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                              MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.editUserImage(userId, file);
    }

    @Operation(summary = "배경 이미지 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/background-image")
    public void updateBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                        MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.editUserBackgroundImage(userId, file);
    }

    private void insertTokenInHeader(TokenResponse tokenResponse) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenResponse.getToken());
    }

}
