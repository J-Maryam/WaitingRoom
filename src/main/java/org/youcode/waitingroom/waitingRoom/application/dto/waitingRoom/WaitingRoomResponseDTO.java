package org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom;

import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Algorithm;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;

import java.time.LocalDate;
import java.util.List;

public record WaitingRoomResponseDTO(
        LocalDate date,
        int capacity,
        Algorithm algorithm,
        TypeMode mode,
        List<VisitorResponseDTO> visits
) {
}
