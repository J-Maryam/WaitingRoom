package org.youcode.waitingroom.waitingRoom.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

public interface WaitingRoomRepository extends JpaRepository<WaitingRoom, Long> {
}
