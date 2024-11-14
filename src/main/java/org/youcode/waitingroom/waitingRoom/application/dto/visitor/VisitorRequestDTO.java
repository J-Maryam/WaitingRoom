package org.youcode.waitingroom.waitingRoom.application.dto.visitor;

import jakarta.validation.constraints.NotBlank;

public record VisitorRequestDTO(
        @NotBlank String name
) {
}
