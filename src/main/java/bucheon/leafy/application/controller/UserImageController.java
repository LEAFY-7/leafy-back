package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserImageService;
import bucheon.leafy.config.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserImageController {

    private final UserImageService userImageService;


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "이미지 등록 성공"),
            @ApiResponse(responseCode = "500", description = "이미지 등록 실패")
    })
    @Operation(summary = "회원 이미지 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/image")
    public void createImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                            MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.createUserImage(userId, file);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "배경 이미지 등록 성공"),
            @ApiResponse(responseCode = "500", description = "배경 이미지 등록 실패")
    })
    @Operation(summary = "배경 이미지 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/background-image")
    public void createBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                      MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.createUserBackgroundImage(userId, file);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "이미지 수정 성공"),
            @ApiResponse(responseCode = "500", description = "이미지 수정 실패")
    })
    @Operation(summary = "회원 이미지 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/image")
    public void updateImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                            MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.editUserImage(userId, file);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "배경 이미지 수정 성공"),
            @ApiResponse(responseCode = "500", description = "배경 이미지 수정 실패")
    })
    @Operation(summary = "배경 이미지 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/background-image")
    public void updateBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                      MultipartFile file) {
        Long userId = authUser.getUserId();
        userImageService.editUserBackgroundImage(userId, file);
    }


}
