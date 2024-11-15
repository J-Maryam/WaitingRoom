package org.youcode.waitingroom.visit.application.dto;

import org.youcode.waitingroom.visit.domain.entity.enums.Status;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.EmbeddableVisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.EmbeddableWaitingRoomResponseDTO;

import java.time.LocalDateTime;

public record VisitResponseDTO(
        LocalDateTime arrivalTime,

        Status status,

        byte priority,

        int estimatedProcessingTime,

        LocalDateTime startTime,

        LocalDateTime endTime,

        EmbeddableVisitorResponseDTO visitor,

        EmbeddableWaitingRoomResponseDTO waitingRoom
) {
}
