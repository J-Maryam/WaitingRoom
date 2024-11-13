package org.youcode.waitingroom.waitingRoom.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.waitingroom.waitingRoom.domain.entity.embeddable.VisitId;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @EmbeddedId
    private VisitId id;

    @PastOrPresent
    private LocalDate arrivalTime;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Status status;

    private byte priority;

    @NotNull
    private int estimatedProcessingTime;

    private LocalDateTime startTime;

    @PastOrPresent
    private LocalDateTime endTime;

    @ManyToOne
    @MapsId("visitorId")
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @ManyToOne
    @MapsId("waitingRoomId")
    @JoinColumn(name = "waiting_room_id")
    private WaitingRoom waitingRoom;

}