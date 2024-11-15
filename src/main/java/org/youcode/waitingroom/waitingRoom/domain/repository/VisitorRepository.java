package org.youcode.waitingroom.waitingRoom.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
