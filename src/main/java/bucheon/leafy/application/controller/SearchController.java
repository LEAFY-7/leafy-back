package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.SearchService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.search.response.goodNameResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "식물 검색")
@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "식물 검색 조회 성공")
    })
    @Operation(summary = "식물 검색")
    @GetMapping
    public ResponseEntity<PageResponse> getSearch(@RequestParam String searchName, PageRequest pageRequest) {
        return ResponseEntity.ok().body(searchService.getSearch(searchName, pageRequest));

    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "식물별 학명 검색 조회 성공")
    })
    @Operation(summary = "식물별 학명 검색")
    @GetMapping("/goodName")
    public ResponseEntity<List<goodNameResponse>> getGoodName(@RequestParam String searchName) {
        return ResponseEntity.ok().body(searchService.getGoodName(searchName));

    }

}