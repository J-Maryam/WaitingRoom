package org.youcode.waitingroom.visit.application.dto;

import java.time.LocalDate;
import java.util.Map;

public record StatisticsDTO(
        double averageWaitingTime,
        double satisfactionRate,
        int visitorCount,
        int peakHourVisitors,
        Map<LocalDate, Double> waitingTimeByDay,
        Map<Integer, Long> visitorsByHour
) {
}
