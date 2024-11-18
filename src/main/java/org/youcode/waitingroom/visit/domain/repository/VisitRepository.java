package org.youcode.waitingroom.visit.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.waitingroom.visit.domain.entity.Visit;
import org.youcode.waitingroom.visit.domain.entity.VisitId;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitId> {
    Page<Visit> findByWaitingRoomId(Long waitingRoomId, Pageable pageable);
    List<Visit> findByWaitingRoomId(Long waitingRoomId);
}
