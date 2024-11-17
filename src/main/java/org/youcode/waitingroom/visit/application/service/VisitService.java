package org.youcode.waitingroom.visit.application.service;

import org.springframework.data.domain.Pageable;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
import org.youcode.waitingroom.common.application.service.GenericService;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.entity.VisitId;

public interface VisitService extends GenericService<Visit, VisitId, VisitRequestDTO, VisitResponseDTO> {
    PagedResponse<VisitResponseDTO> getAllVisitorsForWaitingRoom(Long waitingRoomId, Pageable pageable);
}
