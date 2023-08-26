package bucheon.leafy.domain.feed.response;

import bucheon.leafy.util.response.ScrollResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MainResponse {

    List<PopularTagResponse> popularTags;
    ScrollResponse scrollResponse;

    @Builder
    private MainResponse(List<PopularTagResponse> popularTags,
                        ScrollResponse scrollResponse) {

        this.popularTags = popularTags;
        this.scrollResponse = scrollResponse;
    }

    public static MainResponse of(List<PopularTagResponse> popularTags,
                                  ScrollResponse scrollResponse) {
        return MainResponse.builder()
                .popularTags(popularTags)
                .scrollResponse(scrollResponse)
                .build();
    }

}
