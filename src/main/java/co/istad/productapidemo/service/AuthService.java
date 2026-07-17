package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.auth.RegisterRequest;
import co.istad.productapidemo.dto.auth.RegisterResponse;
import co.istad.productapidemo.dto.user.UserResponse;
import co.istad.productapidemo.dto.user.UserUpdateRequest;

public interface AuthService {
    RegisterResponse register(RegisterRequest req);
    UserResponse updateUser(String keycloakId, UserUpdateRequest request);
}
