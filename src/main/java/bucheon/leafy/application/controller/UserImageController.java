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
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "회원 이미지")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserImageController {

    private final UserImageService userImageService;


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "이미지 등록 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "이미지 등록 실패")
    })
    @Operation(summary = "회원 이미지 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void createImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                            @RequestPart MultipartFile file) {

        Long userId = authUser.getUserId();
        userImageService.createUserImage(userId, file);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "배경 이미지 등록 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "배경 이미지 등록 실패")
    })
    @Operation(summary = "배경 이미지 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/background-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void createBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                      @RequestPart MultipartFile file) {

        Long userId = authUser.getUserId();
        userImageService.createUserBackgroundImage(userId, file);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "이미지 수정 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "이미지 수정 실패")
    })
    @Operation(summary = "회원 이미지 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void updateImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                            @RequestPart MultipartFile file) {

        Long userId = authUser.getUserId();
        if (file != null){
            userImageService.editUserImage(userId, file);
        } else {
            userImageService.deleteUserImage(userId);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "배경 이미지 수정 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "배경 이미지 수정 실패")
    })
    @Operation(summary = "배경 이미지 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/background-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void updateBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                      @RequestPart MultipartFile file) {

        Long userId = authUser.getUserId();
        if (file != null){
            userImageService.editUserBackgroundImage(userId, file);
        } else {
            userImageService.deleteUserBackgroundImage(userId);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "이미지 삭제 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "이미지 삭제 실패")
    })
    @Operation(summary = "회원 이미지 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/image")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void deleteImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        userImageService.deleteUserImage(userId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "배경 이미지 삭제 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "배경 이미지 삭제 실패")
    })
    @Operation(summary = "배경 이미지 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/background-image")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void deleteBackgroundImage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        userImageService.deleteUserBackgroundImage(userId);
    }

}
