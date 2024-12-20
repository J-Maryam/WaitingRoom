package org.youcode.waitingroom.waitingRoom.application.service.Impl;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.waitingroom.common.application.service.GenericServiceImpl;
import org.youcode.waitingroom.common.domain.exception.EntityNotFoundException;
import org.youcode.waitingroom.config.WrmConfigProperties;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.mapper.WaitingRoomMapper;
import org.youcode.waitingroom.waitingRoom.application.service.WaitingRoomService;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;
import org.youcode.waitingroom.waitingRoom.domain.repository.WaitingRoomRepository;

import java.time.LocalDate;

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
        if (requestDto.date() != null && requestDto.date().isBefore(LocalDate.now())) {
            throw new ValidationException("Date must be present or future");
        }
        WaitingRoom waitingRoom = mapper.toEntity(requestDto);

        if (requestDto.mode() == null) {
            waitingRoom.setMode(wrmConfigProperties.getMode());
        }
        if (requestDto.algorithm() == null) {
            waitingRoom.setAlgorithm(wrmConfigProperties.getAlgorithm());
        }
        if (requestDto.capacity() == 0) {
            waitingRoom.setCapacity(wrmConfigProperties.getMaxPerDay());
        }

        WaitingRoom savedEntity = repository.save(waitingRoom);
        return mapper.toDto(savedEntity);
    }

    @Override
    public WaitingRoomResponseDTO update(Long id, WaitingRoomRequestDTO requestDto) {
        WaitingRoom existingWaitingRoom = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WaitingRoom with Id " + id + " not found"));

        WaitingRoom updatedWaitingRoom = mapper.toEntity(requestDto);
        updatedWaitingRoom.setId(id);

        if (updatedWaitingRoom.getMode() == null) {
            updatedWaitingRoom.setMode(existingWaitingRoom.getMode());
        }
        if (updatedWaitingRoom.getAlgorithm() == null) {
            updatedWaitingRoom.setAlgorithm(existingWaitingRoom.getAlgorithm());
        }
        if (updatedWaitingRoom.getCapacity() == 0) {
            updatedWaitingRoom.setCapacity(existingWaitingRoom.getCapacity());
        }

        WaitingRoom savedEntity = repository.save(updatedWaitingRoom);
        return mapper.toDto(savedEntity);
    }
}
