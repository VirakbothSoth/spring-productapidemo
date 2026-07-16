package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.user.CreateUserRequest;
import co.istad.productapidemo.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserByKeycloakId(String keycloakId);
}
