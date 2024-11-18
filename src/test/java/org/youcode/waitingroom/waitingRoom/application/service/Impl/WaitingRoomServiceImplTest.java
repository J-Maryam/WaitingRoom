package org.youcode.waitingroom.waitingRoom.application.service.Impl;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.waitingroom.config.WrmConfigProperties;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.waitingRoom.WaitingRoomResponseDTO;
import org.youcode.waitingroom.waitingRoom.application.mapper.WaitingRoomMapper;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.Algorithm;
import org.youcode.waitingroom.waitingRoom.domain.entity.enums.TypeMode;
import org.youcode.waitingroom.waitingRoom.domain.repository.WaitingRoomRepository;
import org.youcode.waitingroom.common.domain.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WaitingRoomServiceImplTest {

    @Mock
    private WaitingRoomRepository repository;

    @Mock
    private WaitingRoomMapper mapper;

    @Mock
    private WrmConfigProperties wrmConfigProperties;

    @InjectMocks
    private WaitingRoomServiceImpl waitingRoomService;

    private WaitingRoomRequestDTO waitingRoomRequestDTO;
    private WaitingRoom waitingRoom;
    private WaitingRoomResponseDTO waitingRoomResponseDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWaitingRoom_WithDefaultValues() {

        WaitingRoomRequestDTO requestDTO = mock(WaitingRoomRequestDTO.class);
        WaitingRoom waitingRoom = mock(WaitingRoom.class);
        WaitingRoomResponseDTO expectedResponse = mock(WaitingRoomResponseDTO.class);

        when(mapper.toEntity(requestDTO)).thenReturn(waitingRoom);
        when(requestDTO.mode()).thenReturn(null);
        when(requestDTO.algorithm()).thenReturn(null);
        when(requestDTO.capacity()).thenReturn(0);

        when(wrmConfigProperties.getMode()).thenReturn(TypeMode.FULL_TIME);
        when(wrmConfigProperties.getAlgorithm()).thenReturn(Algorithm.FIFO);
        when(wrmConfigProperties.getMaxPerDay()).thenReturn(20);

        when(repository.save(any(WaitingRoom.class))).thenReturn(waitingRoom);
        when(mapper.toDto(waitingRoom)).thenReturn(expectedResponse);

        WaitingRoomResponseDTO actualResponse = waitingRoomService.create(requestDTO);

        assertNotNull(actualResponse);
        verify(waitingRoom).setMode(TypeMode.FULL_TIME);
        verify(waitingRoom).setAlgorithm(Algorithm.FIFO);
        verify(waitingRoom).setCapacity(20);
        verify(repository).save(waitingRoom);
    }

    @Test
    void testUpdateWaitingRoom_NotFound() {
        Long id = 1L;
        WaitingRoomRequestDTO requestDTO = mock(WaitingRoomRequestDTO.class);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            waitingRoomService.update(id, requestDTO);
        });
    }

    @Test
    void testUpdateWaitingRoom_PartialUpdate() {
        Long id = 1L;
        WaitingRoomRequestDTO requestDTO = mock(WaitingRoomRequestDTO.class);
        WaitingRoom existingWaitingRoom = mock(WaitingRoom.class);
        WaitingRoom updatedWaitingRoom = mock(WaitingRoom.class);
        WaitingRoomResponseDTO expectedResponse = mock(WaitingRoomResponseDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(existingWaitingRoom));
        when(mapper.toEntity(requestDTO)).thenReturn(updatedWaitingRoom);

        when(requestDTO.mode()).thenReturn(null);
        when(requestDTO.algorithm()).thenReturn(Algorithm.HPF);
        when(requestDTO.capacity()).thenReturn(0);

        when(existingWaitingRoom.getMode()).thenReturn(TypeMode.FULL_TIME);
        when(existingWaitingRoom.getAlgorithm()).thenReturn(Algorithm.FIFO);
        when(existingWaitingRoom.getCapacity()).thenReturn(20);

        when(repository.save(any(WaitingRoom.class))).thenReturn(updatedWaitingRoom);
        when(mapper.toDto(updatedWaitingRoom)).thenReturn(expectedResponse);

        WaitingRoomResponseDTO actualResponse = waitingRoomService.update(id, requestDTO);

        assertNotNull(actualResponse);
        verify(updatedWaitingRoom).setMode(TypeMode.FULL_TIME);
        verify(updatedWaitingRoom).setAlgorithm(Algorithm.FIFO);
        verify(updatedWaitingRoom).setCapacity(20);
    }

    @Test
    void testUpdateWaitingRoom_WithNullValues() {
        Long id = 1L;
        WaitingRoomRequestDTO requestDTO = new WaitingRoomRequestDTO(
                null,
                0,
                null,
                null
        );
        WaitingRoom existingWaitingRoom = new WaitingRoom()
                .setMode(TypeMode.FULL_TIME)
                .setAlgorithm(Algorithm.FIFO)
                .setCapacity(20)
                .setDate(LocalDate.now());
        WaitingRoomResponseDTO expectedResponse = mock(WaitingRoomResponseDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(existingWaitingRoom));
        when(mapper.toEntity(requestDTO)).thenReturn(existingWaitingRoom);
        when(repository.save(any(WaitingRoom.class))).thenReturn(existingWaitingRoom);
        when(mapper.toDto(existingWaitingRoom)).thenReturn(expectedResponse);

        WaitingRoomResponseDTO actualResponse = waitingRoomService.update(id, requestDTO);

        assertNotNull(actualResponse);
        assertEquals(TypeMode.FULL_TIME, existingWaitingRoom.getMode());
        assertEquals(Algorithm.FIFO, existingWaitingRoom.getAlgorithm());
        assertEquals(20, existingWaitingRoom.getCapacity());
    }

    @Test
    void testCreateWaitingRoom_WithPastDate() {
        WaitingRoomRequestDTO requestDTO = new WaitingRoomRequestDTO(
                LocalDate.now().minusDays(1),
                30,
                null,
                null
        );

        assertThrows(ValidationException.class, () -> {
            waitingRoomService.create(requestDTO);
        });
    }

    @Test
    void testDeleteWaitingRoom_Existing() {
        Long id = 1L;
        WaitingRoom existingRoom = new WaitingRoom().setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(existingRoom));

        waitingRoomService.delete(id);

        verify(repository).delete(existingRoom);
    }

    @Test
    void testDeleteWaitingRoom_NotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            waitingRoomService.delete(id);
        });
    }

}