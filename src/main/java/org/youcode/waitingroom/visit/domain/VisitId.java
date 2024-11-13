package org.youcode.waitingroom.waitingRoom.domain.entity.embeddable;

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
