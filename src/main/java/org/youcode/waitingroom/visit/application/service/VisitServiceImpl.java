package org.youcode.waitingroom.visit.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.common.domain.exception.EntityNotFoundException;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.application.mapper.VisitMapper;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.entity.VisitId;
import org.youcode.waitingroom.visit.domain.repository.VisitRepository;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;
import org.youcode.waitingroom.waitingRoom.domain.repository.VisitorRepository;
import org.youcode.waitingroom.waitingRoom.domain.repository.WaitingRoomRepository;

@Service
@Transactional
@Validated
public class VisitServiceImpl extends GenericServiceImpl<Visit, VisitId, VisitRequestDTO, VisitResponseDTO> implements VisitService {
    private final VisitorRepository visitorRepository;
    private final WaitingRoomRepository waitingRoomRepository;
    private final int defaultPriority;

    public VisitServiceImpl(
            VisitRepository repository,
            VisitMapper mapper,
            VisitorRepository visitorRepository,
            WaitingRoomRepository waitingRoomRepository,
            @Value("${wrm.scheduling.priority}") int defaultPriority
    ) {
        super(repository, mapper);
        this.visitorRepository = visitorRepository;
        this.waitingRoomRepository = waitingRoomRepository;
        this.defaultPriority = defaultPriority;
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

        Visit savedVisit = repository.save(visit);
        return mapper.toDto(savedVisit);
    }
}
