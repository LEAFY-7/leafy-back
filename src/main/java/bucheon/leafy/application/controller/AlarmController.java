package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.AlarmService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "알림")
@RestController
@RequestMapping("/api/v1/alarm")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    // TODO 스크롤 적용해서 코드 수정하기
    @Operation(summary = "유저의 알림 조회")
    @GetMapping
    public ResponseEntity<List<AlarmResponse>> getAlarm(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user) {
        return ResponseEntity.ok().body(alarmService.getAlarm(user));
    }

    // TODO 신규 알림 갯수
    // TODO 유저가 알림 삭제 처리 가능 로직 작성


}
