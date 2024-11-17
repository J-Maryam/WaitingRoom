package org.youcode.waitingroom.visit.application.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.common.domain.exception.EntityNotFoundException;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.application.mapper.VisitMapper;
import org.youcode.waitingroom.visit.application.service.VisitService;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.entity.VisitId;
import org.youcode.waitingroom.visit.domain.entity.enums.Status;
import org.youcode.waitingroom.visit.domain.repository.VisitRepository;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;
import org.youcode.waitingroom.waitingRoom.domain.repository.VisitorRepository;
import org.youcode.waitingroom.waitingRoom.domain.repository.WaitingRoomRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class VisitServiceImpl extends GenericServiceImpl<Visit, VisitId, VisitRequestDTO, VisitResponseDTO> implements VisitService {
    private final VisitorRepository visitorRepository;
    private final WaitingRoomRepository waitingRoomRepository;
    private final int defaultPriority;
    private final VisitRepository repository;

    public VisitServiceImpl(
            VisitRepository repository,
            VisitMapper mapper,
            VisitorRepository visitorRepository,
            WaitingRoomRepository waitingRoomRepository,
            @Value("${wrm.scheduling.priority}") int defaultPriority,
            VisitRepository visitRepository) {
        super(repository, mapper);
        this.visitorRepository = visitorRepository;
        this.waitingRoomRepository = waitingRoomRepository;
        this.defaultPriority = defaultPriority;
        this.repository = repository;
    }

    @Override
    public PagedResponse<VisitResponseDTO> getAllVisitorsForWaitingRoom(Long waitingRoomId, Pageable pageable) {
        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId)
                .orElseThrow(() -> new EntityNotFoundException("Waiting room not found with id: " + waitingRoomId));

        Page<Visit> dtoPage = repository.findByWaitingRoomId(waitingRoomId, pageable);
        List<Visit> visits = dtoPage.getContent();

        if (!visits.isEmpty()) {
            String algorithm = waitingRoom.getAlgorithm().toString();
            visits = sortVisits(visits, algorithm);
        }

        List<VisitResponseDTO> dtoList = visits.stream()
                .map(mapper::toDto)
                .toList();

        return new PagedResponse<>(
                dtoList,
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages(),
                dtoPage.isLast()
        );
    }

    @Override
    public VisitResponseDTO getById(VisitId visitId) {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found with visitorId: " + visitId.visitorId() + " and waitingListId: " + visitId.waitingListId()));
        return mapper.toDto(visit);
    }

    @Override
    public VisitResponseDTO create(VisitRequestDTO visitRequestDTO) {
        Visitor visitor = visitorRepository.findById(visitRequestDTO.visitorId())
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found with id: " + visitRequestDTO.visitorId()));

        WaitingRoom waitingRoom = waitingRoomRepository.findById(visitRequestDTO.waitingRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Waiting room not found with id: " + visitRequestDTO.waitingRoomId()));

        int priority = (visitRequestDTO.priority() == 0) ? defaultPriority : visitRequestDTO.priority();
        Visit visit = mapper.toEntity(visitRequestDTO);
        visit.setPriority(priority);
        visit.setVisitor(visitor);
        visit.setWaitingRoom(waitingRoom);

        if (visit.getStatus() == null) {
            visit.setStatus(Status.WAITING);
        }

        Visit savedVisit = repository.save(visit);
        return mapper.toDto(savedVisit);
    }

    @Override
    public VisitResponseDTO update(VisitId visitId, VisitRequestDTO requestDTO) {
        Visit existingVisit = repository.findById(visitId)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found with visitorId: " + visitId.visitorId() + " and waitingListId: " + visitId.waitingListId()));

        Visitor visitor = visitorRepository.findById(requestDTO.visitorId())
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found with id: " + requestDTO.visitorId()));

        WaitingRoom waitingRoom = waitingRoomRepository.findById(requestDTO.waitingRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Waiting room not found with id: " + requestDTO.waitingRoomId()));

        if (requestDTO.priority() != 0) {
            existingVisit.setPriority(requestDTO.priority());
        }

        if (requestDTO.estimatedProcessingTime() != 0) {
            existingVisit.setEstimatedProcessingTime(requestDTO.estimatedProcessingTime());
        }
        if (requestDTO.arrivalTime() != null) {
            existingVisit.setArrivalTime(LocalDateTime.from(requestDTO.arrivalTime()));
        }
        if (requestDTO.status() != null) {
            existingVisit.setStatus(requestDTO.status());
        }
        if (requestDTO.startTime() != null) {
            existingVisit.setStartTime(requestDTO.startTime());
        }
        if (requestDTO.endTime() != null) {
            existingVisit.setEndTime(requestDTO.endTime());
        }

        if (!requestDTO.visitorId().equals(existingVisit.getVisitor().getId())) {
            existingVisit.setVisitor(visitor);
        }
        if (!requestDTO.waitingRoomId().equals(existingVisit.getWaitingRoom().getId())) {
            existingVisit.setWaitingRoom(waitingRoom);
        }

        Visit updatedVisit = repository.save(existingVisit);
        return mapper.toDto(updatedVisit);
    }

    @Override
    public void delete(VisitId visitId) {
        Visit existingVisit = repository.findById(visitId)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found with visitorId: " + visitId.visitorId() + " and waitingListId: " + visitId.waitingListId()));
        repository.delete(existingVisit);
    }

    private List<Visit> applyFifoOrder(List<Visit> visits) {
        return visits.stream().sorted(Comparator.comparing(Visit::getArrivalTime))
                .collect(Collectors.toList());
    }

    private List<Visit> applyHpfOrder(List<Visit> visits) {
        return visits.stream().sorted(Comparator.comparing(Visit::getPriority))
                .collect(Collectors.toList());
    }

    private List<Visit> applySjfOrder(List<Visit> visits) {
        return visits.stream().sorted(Comparator.comparing(Visit::getEstimatedProcessingTime))
                .collect(Collectors.toList());
    }

    private List<Visit> sortVisits(List<Visit> visits, String algorithm) {
        switch (algorithm.toUpperCase()) {
            case "FIFO":
                return applyFifoOrder(visits);
            case "HPF":
                return applyHpfOrder(visits);
            case "SJF":
                return applySjfOrder(visits);
            default:
                throw new IllegalArgumentException("Unknown scheduling algorithm: " + algorithm);
        }
    }

}

