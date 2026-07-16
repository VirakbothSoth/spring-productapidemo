package co.istad.productapidemo.service.impl;

import co.istad.productapidemo.dto.user.CreateUserRequest;
import co.istad.productapidemo.dto.user.UserResponse;
import co.istad.productapidemo.entity.Profile;
import co.istad.productapidemo.mapper.UserMapper;
import co.istad.productapidemo.repository.UserRepository;
import co.istad.productapidemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        var user = userMapper.mapToEntity(request);
        var profile = new Profile();

        profile.setBio(request.bio());
        profile.setProfileUrl(request.profileUrl());

        user.setProfile(profile);

        return userMapper.mapToResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse getUserByKeycloakId(String keycloakId) {
        return userMapper.mapToResponse(userRepository.findByKeycloakId(keycloakId).orElseThrow(()->new NoSuchElementException("User not found with this id: " + keycloakId)));
    }
}
