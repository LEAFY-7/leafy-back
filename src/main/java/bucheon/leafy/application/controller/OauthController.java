package bucheon.leafy.application.controller;

import bucheon.leafy.config.Oauth2UserService;
import bucheon.leafy.jwt.TokenResponse;
import bucheon.leafy.oauth.OauthRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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

import java.util.HashMap;


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

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;


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

    @GetMapping("/oauth2/code/google")
    public ResponseEntity<TokenResponse> googleOauth2Code(@RequestParam String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v4/token" +
                        "?client_id=" + googleClientId +
                        "&client_secret=" + googleClientSecret +
                        "&code=" + code +
                        "&grant_type=authorization_code" +
                        "&redirect_uri=" + googleRedirectUri,
                        HttpMethod.POST,
                        request,
                        String.class
        );

        String tokenJson = response.getBody();
        JSONObject rjson = new JSONObject(tokenJson);
        String accessToken = rjson.getString("access_token");

        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, Object>> userInfoRequest =
                new HttpEntity<>(null, userInfoHeaders);

        ResponseEntity<String> userResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v1/userinfo?client_id="+googleClientId,
                HttpMethod.GET,
                userInfoRequest,
                String.class
        );

        JSONObject info = new JSONObject(userResponse.getBody());

        OauthRequest oauthRequest = OauthRequest.builder()
                .email(info.getString("email"))
                .image(info.getString("picture"))
                .name(info.getString("name"))
                .password("oauth login password")
                .encodedPassword(passwordEncoder.encode("oauth login password"))
                .provider("google")
                .build();

        TokenResponse tokenResponse = oauth2UserService.oauthLogin(oauthRequest);
        return ResponseEntity.ok().body(tokenResponse);
    }


}
