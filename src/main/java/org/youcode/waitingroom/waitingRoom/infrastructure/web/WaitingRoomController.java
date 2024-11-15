package org.youcode.waitingroom.waitingRoom.infrastructure.web;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
import org.youcode.waitingroom.common.infrastructure.web.GenericController;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

public class WaitingRoomController implements GenericController<Long, WaitingRoomRequestDTO, WaitingRoomResponseDTO> {
    @Override
    public ResponseEntity<PagedResponse<WaitingRoomResponseDTO>> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<WaitingRoomResponseDTO> getById(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<WaitingRoomResponseDTO> create(WaitingRoomRequestDTO waitingRoomRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<WaitingRoomResponseDTO> update(Long aLong, WaitingRoomRequestDTO waitingRoomRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long aLong) {
        return null;
    }
}
