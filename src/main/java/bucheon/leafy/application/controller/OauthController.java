package bucheon.leafy.application.controller;

import bucheon.leafy.config.Oauth2UserService;
import bucheon.leafy.jwt.TokenResponse;
import bucheon.leafy.oauth.OauthRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
@RequiredArgsConstructor
public class OauthController {

    private final Oauth2UserService oauth2UserService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUri;


    @GetMapping("/oauth2/code/{provider}")
    public ResponseEntity<TokenResponse> oauth2Code(@PathVariable String provider,
                                             @RequestParam String code,
                                             @RequestParam String state) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", clientId);
        parameters.add("redirect_uri", redirectUri);
        parameters.add("client_secret", clientSecret);
        parameters.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                tokenUri,
                request,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response.getBody());
        String accessToken = node.get("access_token").asText();

        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, Object>> userInfoRequest =
                new HttpEntity<>(null, userInfoHeaders);

        ResponseEntity<String> userResponse =
                restTemplate.exchange(userInfoUri, HttpMethod.GET, userInfoRequest, String.class);

        JsonNode token = objectMapper.readTree(userResponse.getBody());

        String email = token.path("kakao_account").path("email").asText();
        String image = token.path("properties").path("profile_image").asText();
        String nickname = token.path("properties").path("nickname").asText();
        String password = "oauth login password";
        String encodePassword = passwordEncoder.encode(password);

        OauthRequest oauthRequest = OauthRequest.builder()
                .email(email)
                .image(image)
                .name(nickname)
                .password(password)
                .encodedPassword(encodePassword)
                .provider(provider)
                .build();

        TokenResponse tokenResponse = oauth2UserService.oauthLogin(oauthRequest);
        return ResponseEntity.ok().body(tokenResponse);
    }


}
