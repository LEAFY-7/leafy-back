package bucheon.leafy.config.jwt;


/**
 * JWT 기본 설정값
 */
public class JwtProperties {

    public static final int EXPIRATION_TIME = 18_000_000; // 3시간
    public static final String COOKIE_NAME = "JWT-AUTHENTICATION";

}
