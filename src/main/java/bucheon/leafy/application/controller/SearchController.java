package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.SearchService;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "식물 검색")
@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "식물 검색")
    @GetMapping
    public ResponseEntity<PageResponse> getSearch(@RequestParam String searchName, PageRequest pageRequest) {
        return ResponseEntity.ok().body(searchService.getSearch(searchName, pageRequest));

    }

}
