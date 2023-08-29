package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.EmailSendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원정보")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class EmailSendController {

    private final EmailSendService emailSendService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "임시 비밀번호 발급 성공"),
            @ApiResponse(responseCode = "422", description = "임시 비밀번호 이메일 발송 실패"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 email or phone")
    })
    @Operation(summary = "임시 비밀번호 발급")
    @PatchMapping("/temporary-password")
    public void updateTemporaryPassword(@RequestParam String email, String phone) {
        emailSendService.updateTemporaryPassword(email, phone);
    }

    // 아이피 주소 받는 것을 고려
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "이메일 전송 성공"),
            @ApiResponse(responseCode = "422", description = "이메일 전송 실패")
    })
    @Operation(summary = "이메일 인증 메일 보내기")
    @PostMapping("/email-send")
    public void sendEmail(@RequestParam String email) {
        emailSendService.sendCertificationNumber(email);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "인증번호 검증 성공"),
            @ApiResponse(responseCode = "403", description = "인증번호 검증 실패")
    })
    @Operation(summary = "이메일 인증 번호 확인")
    @GetMapping("/email-confirm")
    public void emailConfirm(@RequestParam String email, @RequestParam String number) {
        emailSendService.confirmCertificationNumber(email, number);
    }

}
