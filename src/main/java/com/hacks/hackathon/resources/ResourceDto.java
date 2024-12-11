package com.hacks.hackathon.resources;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public record ResourceDto(
        @Enumerated(EnumType.STRING)
        ResourceType resourceType,
        Long quantity
) {
}
