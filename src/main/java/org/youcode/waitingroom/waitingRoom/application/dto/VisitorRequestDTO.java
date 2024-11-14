package org.youcode.waitingroom.waitingRoom.application.dto;

import jakarta.validation.constraints.NotBlank;

public record VisitorRequestDTO(
        @NotBlank String name
) {
}
