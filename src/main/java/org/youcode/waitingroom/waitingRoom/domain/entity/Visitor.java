package org.youcode.waitingroom.waitingRoom.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youcode.waitingroom.visit.domain.entity.Visit;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "visitor")
    private List<Visit> visits;

}
