package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.SearchService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PageResponse> getSearch(@RequestParam String keyword,
                                                  @Parameter(description = "page, limit, sortColum, sortStatus 전부 옵션, " +
                                                          "식물 검색에서 sortColum, sortStatus는 데이터 보내도 해당 데이터로 정렬안됨, 디폴트 goodName(품종명)으로 정렬") PageRequest pageRequest) {
        return ResponseEntity.ok().body(searchService.getSearch(keyword, pageRequest));

    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "식물별 학명 검색 조회 성공")
    })
    @Operation(summary = "식물별 학명 검색")
    @GetMapping("/good-name")
    public ResponseEntity<List<goodNameResponse>> getGoodName(@RequestParam String keyword) {
        return ResponseEntity.ok().body(searchService.getGoodName(keyword));

    }

}