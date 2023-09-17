package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.UserRole;
import bucheon.leafy.domain.user.request.PasswordRequest;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.request.UserRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
            @ApiResponse(responseCode = "409", description = "아이디나 전화번호가 이미 존재"),
            @ApiResponse(responseCode = "401, 500", description = "서버 코드 문제로 회원가입 실패")
    })
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        SignInRequest signInRequest = SignInRequest.of(signUpRequest);

        TokenResponse tokenResponse = userService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
            @ApiResponse(responseCode = "401, 404", description = "아이디나 비밀번호가 틀림"),
            @ApiResponse(responseCode = "500", description = "서버 코드 문제로 로그인 실패")
    })
    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody SignInRequest signInRequest) {
        TokenResponse tokenResponse = userService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "회원 탈퇴 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
    })
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdrawal(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        userService.deleteUser(userId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "중복되는 아이디 (이메일)이 존재하지 않음"),
            @ApiResponse(responseCode = "409", description = "아이디 (이메일)이 이미 존재")
    })
    @Operation(summary = "아이디, 이메일 중복체크")
    @GetMapping("/check/email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void emailCheck(@RequestParam String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "형식이 맞지 않습니다.");
        }

        userService.duplicationEmailCheck(email);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "중복되는 닉네임이 존재하지 않음"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
            @ApiResponse(responseCode = "409", description = "닉네임이 이미 존재")
    })
    @Operation(summary = "닉네임 중복체크")
    @GetMapping("/check/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void nickNameCheck(@RequestParam String nickName) {
        Pattern pattern = Pattern.compile("^(?!admin|leafy)(?!.*\\\\s{2,})(?!.*\\\\s$)(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9_가-힣\\\\s]{3,12}$");
        Matcher matcher = pattern.matcher(nickName);

        if (!matcher.matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "형식이 맞지 않습니다.");
        }

        userService.duplicationNickNameCheck(nickName);
    }

    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Operation(summary = "Get Me")
    @GetMapping("/me")
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {

        Long userId = authUser.getUserId();
        GetMeResponse getMe = userService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }

    @ApiResponse(responseCode = "200", description = "수정 성공")
    @Operation(summary = "회원 정보 수정")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PutMapping
    public void editUser(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                         @RequestBody UserRequest userRequest) {
        Long userId = authUser.getUserId();
        userService.updateUser(userId, userRequest);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 체크 불통과"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
    })
    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void updatePassword(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                               @RequestBody @Valid PasswordRequest passwordRequest) {

        Long userId = authUser.getUserId();
        userService.editPassword(userId, passwordRequest);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "이메일 조회"),
            @ApiResponse(responseCode = "404", description = "이름과 전화번호로 가입된 이메일이 존재하지 않음")
    })
    @Operation(summary = "아이디 찾기")
    @GetMapping("/email")
    public String getUserEmail(@RequestParam String name, String phone) {
        return userService.getEmailByNameAndPhone(name, phone);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 비공개 수정 성공"),
            @ApiResponse(responseCode = "500", description = "회원 비공개 수정 실패"),
    })
    @Operation(summary = "회원 비공개 수정")
    @PatchMapping("/hide")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editIsHide(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        userService.editIsHide(userId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "전체 알람 비공개 on, off 성공"),
            @ApiResponse(responseCode = "500", description = "전체 알람 비공개 on, off 실패"),
    })
    @Operation(summary = "전체 알람 비공개 on, off")
    @PatchMapping("/all-notification")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editIsAllNotifications(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        userService.editIsAllNotifications(userId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 알람 비공개 on, off 성공"),
            @ApiResponse(responseCode = "500", description = "댓글 알람 비공개 on, off 실패"),
    })
    @Operation(summary = "댓글 알람 on, off")
    @PatchMapping("/comment-notification")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editIsCommentNotifications(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        userService.editIsCommentNotifications(userId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 권한 수정 성공"),
            @ApiResponse(responseCode = "500", description = "회원 권한 수정 실패"),
    })
    @Operation(summary = "회원 권한 수정")
    @PatchMapping("/role/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editRole(@PathVariable Long userId, @RequestParam UserRole userRole) {
        userService.editRole(userId, userRole);
    }

}
