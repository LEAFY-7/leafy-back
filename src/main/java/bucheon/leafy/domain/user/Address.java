package bucheon.leafy.domain.user;

import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String zoneCode;

    private String address;

    private String jibunAddress;

    private String roadAddress;

    private String detailAddress;

    private Boolean isHide;

    @Builder
    private Address(String zoneCode, String address, String jibunAddress,
                    String roadAddress, String detailAddress, Boolean isHide) {
        this.zoneCode = zoneCode;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.isHide = isHide;
    }

    public static Address of(SignUpRequest signUpRequest) {
        return Address.builder()
                .zoneCode(signUpRequest.getZoneCode())
                .address(signUpRequest.getAddress())
                .jibunAddress(signUpRequest.getJibunAddress())
                .roadAddress(signUpRequest.getRoadAddress())
                .detailAddress(signUpRequest.getDetailAddress())
                .isHide(signUpRequest.getAddressIsHide())
                .build();
    }

}
