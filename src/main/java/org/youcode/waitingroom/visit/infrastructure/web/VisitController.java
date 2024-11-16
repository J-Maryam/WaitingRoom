package org.youcode.waitingroom.visit.infrastructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.waitingroom.common.infrastructure.web.GenericControllerImpl;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.application.service.VisitService;
import org.youcode.waitingroom.visit.domain.entity.Visit;

@RestController
@RequestMapping("/api/visits")
public class VisitController extends GenericControllerImpl<Visit, Long, VisitRequestDTO, VisitResponseDTO> {
    public VisitController(VisitService service) {
        super(service);
    }
}
