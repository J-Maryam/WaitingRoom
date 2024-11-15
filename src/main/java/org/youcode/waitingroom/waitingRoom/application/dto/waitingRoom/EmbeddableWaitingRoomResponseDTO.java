package org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom;

import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Algorithm;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;

import java.time.LocalDate;

public record EmbeddableWaitingRoomResponseDTO(
        Long id,
        LocalDate date,
        int capacity,
        Algorithm algorithm,
        TypeMode mode
) {
}
