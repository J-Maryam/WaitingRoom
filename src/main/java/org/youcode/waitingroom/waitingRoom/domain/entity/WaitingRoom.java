package org.youcode.waitingroom.waitingRoom.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @FutureOrPresent
    private LocalDate date;

    @NotNull
    private int capacity;

    @NotBlank
    private String algorithm;

    @Enumerated(EnumType.STRING)
    private TypeMode mode;
}
