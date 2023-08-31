package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.Oauth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
public class OauthController {

    private final Oauth2UserService oauth2UserService;

    @GetMapping("/oauth2/code/{provider}")
    public ResponseEntity<String> oauth2Code(@PathVariable String provider, @RequestParam String code, @RequestParam String state) {
        log.info("provider = {}", provider);
        log.info("code = {}", code);
        log.info("state = {}", state);
        log.info("a = {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        OAuth2User oauth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = oauth2User.getName();
        Map<String, Object> attributes = oauth2User.getAttributes();

        log.info("attributes = ", attributes);


        return ResponseEntity.ok().body("OAuth2 로그인 성공: " + userName);
    }

}
