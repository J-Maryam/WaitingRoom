package org.youcode.waitingroom.waitingRoom.application.dto.visitor;

import jakarta.validation.constraints.NotBlank;
import org.youcode.waitingroom.visit.domain.Visit;

import java.util.List;

public record VisitorResponseDTO(
        Long id,
        String name,
        List<VisitorResponseDTO> visits
) {
}
