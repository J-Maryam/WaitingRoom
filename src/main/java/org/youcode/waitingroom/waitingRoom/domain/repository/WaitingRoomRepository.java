package org.youcode.waitingroom.waitingRoom.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.waitingroom.waitingRoom.domain.entity.WaitingRoom;

@Repository
public interface WaitingRoomRepository extends JpaRepository<WaitingRoom, Long> {
}
