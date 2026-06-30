package co.istad.productapidemo.dto;

import lombok.Builder;

@Builder
public record TagRequest(
        String name
) {
}
