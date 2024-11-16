package org.youcode.waitingroom.visit.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.waitingroom.visit.domain.entity.enums.Status;

import java.time.LocalDateTime;

public record VisitRequestDTO(
        @NotNull
        Long visitorId,

        @NotNull
        Long waitingRoomId,

        @NotNull
        @PastOrPresent
        LocalDateTime arrivalTime,

        @NotNull
        Status status,

        int priority,

        @NotNull
        int estimatedProcessingTime,

        LocalDateTime startTime,

        @PastOrPresent
        LocalDateTime endTime
) {
}
