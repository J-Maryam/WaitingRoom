package org.youcode.waitingroom.waitingRoom.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.config.WrmConfigProperties;
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

    private final WrmConfigProperties wrmConfigProperties;
    private final WaitingRoomRepository repository;
    private final WaitingRoomMapper mapper;

    public WaitingRoomServiceImpl(WaitingRoomRepository repository, WaitingRoomMapper mapper, WrmConfigProperties wrmConfigProperties) {
        super(repository, mapper);
        this.wrmConfigProperties = wrmConfigProperties;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public WaitingRoomResponseDTO create(WaitingRoomRequestDTO requestDto) {
        WaitingRoom waitingRoom = mapper.toEntity(requestDto);

        waitingRoom.setMode(wrmConfigProperties.getMode());
        waitingRoom.setAlgorithm(wrmConfigProperties.getAlgorithm());
        waitingRoom.setCapacity(wrmConfigProperties.getMaxPerDay());

        WaitingRoom savedEntity = repository.save(waitingRoom);
        return mapper.toDto(savedEntity);
    }
}