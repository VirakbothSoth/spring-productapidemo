package co.istad.productapidemo.dto.user;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        String firstName,
        String lastName,
        String gender,
        String biography,
        String profileUrl
) {
}
