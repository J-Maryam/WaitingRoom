package org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Algorithm;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;

import java.time.LocalDate;

public record WaitingRoomRequestDTO(

        @NotNull
        @FutureOrPresent
        LocalDate date,

        int capacity,

        Algorithm algorithm,

        TypeMode mode
) {
}
