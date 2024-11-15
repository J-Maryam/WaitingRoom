package org.youcode.waitingroom.waitingRoom.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.service.WaitingRoomService;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class WaitingRoomServiceImpl extends GenericServiceImpl<WaitingRoom, Long, WaitingRoomRequestDTO, WaitingRoomResponseDTO> implements WaitingRoomService {
}
