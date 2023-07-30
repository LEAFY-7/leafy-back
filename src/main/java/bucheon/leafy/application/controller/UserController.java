package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.request.PasswordRequest;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.jwt.JwtFilter;
import bucheon.leafy.jwt.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
            @ApiResponse(responseCode = "409", description = "아이디나 닉네임이 이미 존재"),
            @ApiResponse(responseCode = "401, 500", description = "서버 코드 문제로 회원가입 실패")
    })
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        Long userId = userService.signUp(signUpRequest);

        SignInRequest signInRequest = SignInRequest.of(signUpRequest);

        TokenResponse tokenResponse = userService.signIn(signInRequest);
        tokenResponse.addUserId(userId);
        insertTokenInHeader(tokenResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
            @ApiResponse(responseCode = "401", description = "비밀번호가 틀림"),
            @ApiResponse(responseCode = "404", description = "아이디가 틀림"),
            @ApiResponse(responseCode = "500", description = "서버 코드 문제로 로그인 실패")
    })
    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody SignInRequest signInRequest) {
        TokenResponse tokenResponse = userService.signIn(signInRequest);
        insertTokenInHeader(tokenResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "중복되는 아이디 (이메일)이 존재하지 않음"),
            @ApiResponse(responseCode = "409", description = "아이디 (이메일)이 이미 존재")
    })
    @Operation(summary = "아이디, 이메일 중복체크")
    @PostMapping("/check/email")
    @ResponseStatus(HttpStatus.CREATED)
    public void emailCheck(@RequestBody String email) {
        userService.duplicationIdCheck(email);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "중복되는 닉네임이 존재하지 않음"),
            @ApiResponse(responseCode = "409", description = "닉네임이 이미 존재")
    })
    @Operation(summary = "닉네임 중복체크")
    @PostMapping("/check/nickname")
    @ResponseStatus(HttpStatus.CREATED)
    public void nickNameCheck(@RequestBody String nickName) {
        userService.duplicationNickNameCheck(nickName);
    }

    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Operation(summary = "Get Me")
    @GetMapping
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = userService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
    })
    @Operation(summary = "비밀번호 변경")
    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                               @RequestBody @Valid PasswordRequest passwordRequest) {

        Long userId = authUser.getUserId();
        userService.editPassword(userId, passwordRequest);
    }

    private void insertTokenInHeader(TokenResponse tokenResponse) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenResponse.getToken());
    }

}
