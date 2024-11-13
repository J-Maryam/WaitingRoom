package org.youcode.waitingroom.waitingRoom.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Status;

import java.time.LocalDate;

@Entity
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    exist annotation
    private Long id;

    @NotBlank
//    unique value annotation
    private String name;

    @PastOrPresent
    private LocalDate arrivalTime;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Status status;

    private byte priority;

    @NotNull
    private int estimatedProcessingTime;

    private LocalDate startTime;

    @PastOrPresent
    private LocalDate endTime;
}
