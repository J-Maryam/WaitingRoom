package org.youcode.waitingroom.waitingRoom.application.mapper;

import org.youcode.waitingroom.common.application.mapper.GenericMapper;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

public interface WaitingRoomMapper extends GenericMapper<WaitingRoom, WaitingRoomRequestDTO, WaitingRoomResponseDTO> {
}
