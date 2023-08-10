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
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "zone_code", nullable = false)
    private String zoneCode; // 우편번호

    @Column(name = "address", nullable = false)
    private String address; // 주소

    @Column(name = "jibun_address", nullable = false)
    private String jibunAddress; // 지번주소

    @Column(name = "road_address", nullable = false)
    private String roadAddress; // 도로명주소

    @Column(name = "detail_address", nullable = false)
    private String detailAddress; // 상세주소

    @Column(name = "is_hide", nullable = false)
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

    public void addUser(User user) {
        this.user = user;
    }

}
