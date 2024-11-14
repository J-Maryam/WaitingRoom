package org.youcode.waitingroom.visit.application.dto;

import org.youcode.waitingroom.visit.domain.enums.Status;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.EmbeddableVisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.EmbeddableWaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;

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
