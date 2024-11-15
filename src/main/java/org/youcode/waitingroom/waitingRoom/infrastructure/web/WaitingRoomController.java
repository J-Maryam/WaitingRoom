package org.youcode.waitingroom.waitingRoom.infrastructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.waitingroom.common.infrastructure.web.GenericControllerImpl;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.service.WaitingRoomService;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

@RequestMapping("/api/waiting_list")
@RestController
public class WaitingRoomController extends GenericControllerImpl<WaitingRoom, Long, WaitingRoomRequestDTO, WaitingRoomResponseDTO> {
    public WaitingRoomController(WaitingRoomService service) {
        super(service);
    }
}
