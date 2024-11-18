package org.youcode.waitingroom.visit.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.waitingroom.visit.application.dto.StatisticsDTO;
import org.youcode.waitingroom.visit.application.service.StatisticsService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService service;

    @GetMapping
    public ResponseEntity<StatisticsDTO> getStatistics(
            @RequestParam("waitingRoomId") Long waitingRoomId) {

        StatisticsDTO statistics = service.statistics(waitingRoomId);
        return ResponseEntity.ok(statistics);
    }
}
