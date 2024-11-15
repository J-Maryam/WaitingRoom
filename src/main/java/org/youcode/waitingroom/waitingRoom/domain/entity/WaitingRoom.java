package org.youcode.waitingroom.waitingRoom.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Algorithm;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WaitingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    private int capacity;

    private Algorithm algorithm;

    @Enumerated(EnumType.STRING)
    private TypeMode mode;

    @OneToMany(mappedBy = "waitingRoom")
    private List<Visit> visits;
}
