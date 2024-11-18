package org.youcode.waitingroom.visit.application.service;

import org.youcode.waitingroom.visit.application.dto.StatisticsDTO;

public interface StatisticsService {
    StatisticsDTO statistics(Long waitingRoomI);
}
