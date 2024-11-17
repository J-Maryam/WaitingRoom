package org.youcode.waitingroom.visit.infrastructure.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
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
    private final VisitService visitService;

    public VisitController(VisitService service) {
        super(service);
        this.visitService = service;
    }

    @GetMapping("/waiting-room/{waitingRoomId}")
    public ResponseEntity<ApiResponse<PagedResponse<VisitResponseDTO>>> getAllVisitorsForWaitingRoom(
            @PathVariable Long waitingRoomId,
            Pageable pageable) {

        PagedResponse<VisitResponseDTO> visits = visitService.getAllVisitorsForWaitingRoom(waitingRoomId, pageable);
        return ResponseEntity.ok(ApiResponse.success(visits, "Visitors retrieved successfully"));
    }

    @GetMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<ApiResponse<VisitResponseDTO>> getById(
            @PathVariable Long visitorId,
            @PathVariable Long waitingListId) {

        VisitResponseDTO visitDto = service.getById(new VisitId(visitorId, waitingListId));
        return ResponseEntity.ok(ApiResponse.success(visitDto, "Visit retrieved successfully"));
    }

    @PutMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<ApiResponse<VisitResponseDTO>> update(
            @PathVariable Long visitorId,
            @PathVariable Long waitingListId,
            @RequestBody @Valid VisitRequestDTO visitRequestDTO) {

        VisitResponseDTO updatedVisit = service.update(new VisitId(visitorId, waitingListId), visitRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(updatedVisit, "Visit updated successfully"));
    }

    @DeleteMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long visitorId,
            @PathVariable Long waitingListId) {

        service.delete(new VisitId(visitorId, waitingListId));
        return ResponseEntity.ok(ApiResponse.success(null, "Visit deleted successfully"));
    }
}
