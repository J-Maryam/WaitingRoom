package org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.youcode.waitingroom.common.application.validation.annotation.UniqueValue;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;
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
