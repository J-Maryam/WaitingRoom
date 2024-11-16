package org.youcode.waitingroom.visit.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.application.mapper.VisitMapper;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.repository.VisitRepository;

@Service
@Transactional
@Validated
public class VisitServiceImpl extends GenericServiceImpl<Visit, Long, VisitRequestDTO, VisitResponseDTO> implements VisitService {
    public VisitServiceImpl(VisitRepository repository, VisitMapper mapper) {
        super(repository, mapper);
    }
}
