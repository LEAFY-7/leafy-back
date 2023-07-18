package bucheon.leafy.domain.feed.response;


import lombok.Builder;
import lombok.Data;

public interface PopularTagInformation {

    String getTag();
    Long getCount();

    @Data
    class PopularTagResponse {

        private String tag;
        private Long count;

        @Builder
        private PopularTagResponse(String tag, Long count) {
            this.tag = tag;
            this.count = count;
        }

        public static PopularTagResponse of(PopularTagInformation popularTagInformation){
            return PopularTagResponse.builder()
                    .tag(popularTagInformation.getTag())
                    .count(popularTagInformation.getCount())
                    .build();
        }

    }

}
