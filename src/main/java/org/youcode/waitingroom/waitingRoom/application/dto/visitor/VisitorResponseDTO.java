package org.youcode.waitingroom.waitingRoom.application.dto.visitor;

import java.util.List;

public record VisitorResponseDTO(
        Long id,
        String name,
        List<VisitorResponseDTO> visits
) {
}
