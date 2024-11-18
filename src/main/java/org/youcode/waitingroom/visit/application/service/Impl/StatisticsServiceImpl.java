package org.youcode.waitingroom.visit.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.youcode.waitingroom.visit.application.dto.StatisticsDTO;
import org.youcode.waitingroom.visit.application.service.StatisticsService;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.entity.enums.Status;
import org.youcode.waitingroom.visit.domain.repository.VisitRepository;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final VisitRepository repository;

    private static final int WAITING_TIME_THRESHOLD = 30;

    public StatisticsDTO statistics(Long waitingRoomId) {
        List<Visit> visits = repository.findByWaitingRoomId(waitingRoomId);

        double averageWaitingTime = calculateAverageWaitingTime(visits);
        double satisfactionRate = calculateEstimatedSatisfactionRate(visits);
        int visitorCount = visits.size();
        double visitorRotation = calculateTurnoverRate(visits);
        return new StatisticsDTO(
                averageWaitingTime,
                satisfactionRate,
                visitorCount,
                visitorRotation
        );
    }

    private double calculateAverageWaitingTime(List<Visit> visits) {
        return visits.stream()
                .filter(v -> v.getStartTime() != null && v.getArrivalTime() != null)
                .filter(v -> v.getStatus() == Status.FINISHED)
                .mapToLong(v -> Duration.between(v.getArrivalTime(), v.getStartTime()).toMinutes())
                .average()
                .orElse(0.0);
    }

    private double calculateEstimatedSatisfactionRate(List<Visit> visits) {
        if (visits.isEmpty()) return 0.0;

        long satisfiedVisits = visits.stream()
                .filter(visit -> visit.getStatus() == Status.FINISHED)
                .filter(visit -> {
                    if (visit.getArrivalTime() == null || visit.getStartTime() == null) {
                        return false;
                    }
                    long waitingTime = Duration.between(visit.getArrivalTime(), visit.getStartTime()).toMinutes();
                    return waitingTime <= WAITING_TIME_THRESHOLD;
                })
                .count();

        return ((double) satisfiedVisits / visits.size()) * 100;
    }

    private double calculateTurnoverRate(List<Visit> visits) {
        if (visits.isEmpty()) return 0.0;

        long completedVisits = visits.stream()
                .filter(v -> v.getStatus() == Status.FINISHED)
                .count();

        Duration totalDuration = visits.stream()
                .filter(v -> v.getArrivalTime() != null && v.getStartTime() != null)
                .filter(v -> v.getStatus() == Status.FINISHED)
                .map(visit -> Duration.between(visit.getArrivalTime(), visit.getStartTime()))
                .reduce(Duration::plus)
                .orElse(Duration.ZERO);

        double hours = Math.max(1.0, totalDuration.toHours());
        return completedVisits / hours;
    }

}