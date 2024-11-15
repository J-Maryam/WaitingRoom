package org.youcode.waitingroom.waitingRoom.application.dto.visitor;

import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;

import java.util.List;

public record VisitorResponseDTO(
        Long id,
        String name,
        List<VisitResponseDTO> visits
) {
}
