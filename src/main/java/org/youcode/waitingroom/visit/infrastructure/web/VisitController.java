package org.youcode.waitingroom.visit.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.waitingroom.common.infrastructure.web.GenericControllerImpl;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.application.service.VisitService;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.entity.VisitId;
import org.youcode.waitingroom.waitingRoom.application.dto.ApiResponse;

@RestController
@RequestMapping("/api/visits")
public class VisitController extends GenericControllerImpl<Visit, VisitId, VisitRequestDTO, VisitResponseDTO> {
    public VisitController(VisitService service) {
        super(service);
    }

    @GetMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<ApiResponse<VisitResponseDTO>> getById(
            @PathVariable Long visitorId,
            @PathVariable Long waitingListId) {

        VisitResponseDTO visitDto = service.getById(new VisitId(visitorId, waitingListId));
        return ResponseEntity.ok(ApiResponse.success(visitDto, "Visit retrieved successfully"));
    }
}
