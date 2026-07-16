package co.istad.productapidemo.mapper;

import co.istad.productapidemo.dto.auth.RegisterResponse;
import co.istad.productapidemo.dto.user.CreateUserRequest;
import co.istad.productapidemo.dto.user.UserResponse;
import co.istad.productapidemo.entity.User;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target="profileUrl", source="profile.profileUrl")
    @Mapping(target="bio", source="profile.bio")
    UserResponse mapToResponse(User user);
    User mapToEntity(CreateUserRequest userRequest);

    // for registering user
    RegisterResponse toRegisterResponse(UserRepresentation user);
}
