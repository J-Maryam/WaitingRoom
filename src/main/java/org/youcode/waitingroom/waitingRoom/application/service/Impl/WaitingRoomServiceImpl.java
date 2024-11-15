package org.youcode.waitingroom.waitingRoom.application.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.mapper.WaitingRoomMapper;
import org.youcode.waitingroom.waitingRoom.application.service.WaitingRoomService;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;
import org.youcode.waitingroom.waitingRoom.domain.repository.WaitingRoomRepository;

@Service
@Transactional
@Validated
public class WaitingRoomServiceImpl extends GenericServiceImpl<WaitingRoom, Long, WaitingRoomRequestDTO, WaitingRoomResponseDTO> implements WaitingRoomService {

    public WaitingRoomServiceImpl(WaitingRoomRepository repository, WaitingRoomMapper mapper) {
        super(repository, mapper);
    }
}
