package org.youcode.waitingroom.visit.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VisitId implements Serializable {
    private Long visitorId;
    private Long waitingRoomId;
}
