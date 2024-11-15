package org.youcode.waitingroom.visit.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record VisitId(
        @Column(name = "visitor_id") Long visitorId,
        @Column(name = "waiting_list_id") Long waitingListId
) implements Serializable {
}
