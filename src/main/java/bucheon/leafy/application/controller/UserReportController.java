package bucheon.leafy.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 신고")
@RestController
@RequestMapping("/api/v1/users/{userId}/report")
@RequiredArgsConstructor
public class UserReportController {

}