package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.user.UserResponse;
import co.istad.productapidemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
@Slf4j
public class TestController {
    private final UserService userService;

    @GetMapping("/profile")
    public UserResponse profile(@AuthenticationPrincipal Jwt jwt) {
        String keycloakId = (String) jwt.getClaims().get("keycloakId");
        log.info("Profile keycloakId: {}", keycloakId);
        return userService.getUserByKeycloakId(keycloakId);
    }
}
