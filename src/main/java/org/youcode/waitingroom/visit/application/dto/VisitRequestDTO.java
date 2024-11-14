package org.youcode.waitingroom.visit.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.waitingroom.visit.domain.enums.Status;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;

import java.time.LocalDateTime;

public record VisitRequestDTO(
        @NotNull
        @PastOrPresent
        LocalDateTime arrivalTime,

        @NotBlank
        Status status,

        byte priority,

        @NotNull
        int estimatedProcessingTime,

        LocalDateTime startTime,

        @PastOrPresent
        LocalDateTime endTime,

        @NotNull
        VisitorResponseDTO visitor,

        @NotNull
        WaitingRoomResponseDTO waitingRoom
) {
}
