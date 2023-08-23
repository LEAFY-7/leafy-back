package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.leafyapi.LeafyApiDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
class SearchServiceTest extends IntegrationTestSupport {

    @Autowired
    SearchService searchService;

    @Test
    @DisplayName("식물 정보를 저장한다")
    void testSaveSearch(){
        //given
        List<LeafyApiDto> dto = new ArrayList<>();
        dto.add(createSearch());

        //when
        int result = searchService.saveSearch(dto);

        //then
        assertThat(result)
                .isEqualTo(1);

    }

    private LeafyApiDto createSearch() {
        return LeafyApiDto.builder()
                .saleDate("2023-06-18")
                .flowerGubn("절화")
                .pumName("장미")
                .goodName("블론디")
                .lv("특3")
                .maxAmt(1200)
                .minAmt(1200)
                .avgAmt(1200)
                .build();
    }

}