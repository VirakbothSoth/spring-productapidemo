package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.user.CreateUserRequest;
import co.istad.productapidemo.dto.user.UserResponse;
import co.istad.productapidemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse createNew(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
