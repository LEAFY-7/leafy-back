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
    @Column(name = "address_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String zoneCode; // 우편번호
    private String address; // 주소
    private String jibunAddress; // 지번주소
    private String roadAddress; // 도로명주소
    private String detailAddress; // 상세주소

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
