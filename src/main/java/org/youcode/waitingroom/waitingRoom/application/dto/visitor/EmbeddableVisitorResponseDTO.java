package org.youcode.waitingroom.waitingRoom.application.dto.visitor;

import org.youcode.waitingroom.visit.domain.Visit;

import java.util.List;

public record EmbeddableVisitorResponseDTO(
        Long id,
        String name
) {
}
