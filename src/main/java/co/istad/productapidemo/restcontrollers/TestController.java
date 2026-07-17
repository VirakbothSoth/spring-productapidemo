package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.user.UserResponse;
import co.istad.productapidemo.dto.user.UserUpdateRequest;
import co.istad.productapidemo.service.AuthService;
import co.istad.productapidemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
@Slf4j
public class TestController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/profile")
    public UserResponse profile(@AuthenticationPrincipal Jwt jwt) {
        String keycloakId = (String) jwt.getClaims().get("keycloakId");
        log.info("Profile keycloakId: {}", keycloakId);
        return userService.getUserByKeycloakId(keycloakId);
    }

    @PostMapping("/forgot-password/{email}")
    public String forgotPassword(@PathVariable String email) {
        authService.forgotPassword(email);
        return "Reset password link has been sent to associate acc";
    }

    @PutMapping("/profile")
    public UserResponse updateProfile(@AuthenticationPrincipal Jwt jwt, @RequestBody UserUpdateRequest request) {
        String keycloakId = (String) jwt.getSubject();
        return authService.updateUser(keycloakId,jwt.getClaim("email"));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    @GetMapping("/customer")
    public String customer() { return "Hello! I am the customer";}

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @GetMapping("/seller")
    public String seller() { return "Hello! I am the seller "; }
}
