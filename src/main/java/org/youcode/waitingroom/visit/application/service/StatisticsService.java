package org.youcode.waitingroom.visit.application.service;

import org.youcode.waitingroom.visit.application.dto.StatisticsDTO;

import java.time.LocalDateTime;

public interface StatisticsService {
    StatisticsDTO StatisticsStatisticsDTO(Long waitingRoomId, LocalDateTime startDate, LocalDateTime endDate);
}
