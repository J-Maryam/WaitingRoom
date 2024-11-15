package org.youcode.waitingroom.waitingRoom.application.service;

import org.youcode.waitingroom.common.application.service.GenericService;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

public interface WaitingRoomService extends GenericService<WaitingRoom, Long, WaitingRoomRequestDTO, WaitingRoomResponseDTO> {
}
