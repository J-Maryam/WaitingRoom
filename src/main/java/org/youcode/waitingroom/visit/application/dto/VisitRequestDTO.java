package org.youcode.waitingroom.visit.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.waitingroom.common.application.validation.annotation.Exists;
import org.youcode.waitingroom.visit.domain.entity.VisitId;
import org.youcode.waitingroom.visit.domain.entity.enums.Status;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

import java.time.LocalDateTime;

public record VisitRequestDTO(
        @NotNull
        Long visitorId,

        @NotNull
        Long waitingRoomId,

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
        LocalDateTime endTime
) {
}
