package org.youcode.waitingroom.visit.application.dto;

public record StatisticsDTO(
        double averageWaitingTime,
        double satisfactionRate,
        int visitorCount,
        double visitorRotation
) {
}
