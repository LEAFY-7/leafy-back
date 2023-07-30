package bucheon.leafy.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "피드 차단")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/block")
@RequiredArgsConstructor
public class FeedBlockController {

}
