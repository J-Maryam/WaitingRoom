package org.youcode.waitingroom.visit.application.service;

import org.youcode.waitingroom.common.application.service.GenericService;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorResponseDTO;

public interface VisitService extends GenericService<Visit, Long, VisitorResponseDTO, VisitResponseDTO> {
}
