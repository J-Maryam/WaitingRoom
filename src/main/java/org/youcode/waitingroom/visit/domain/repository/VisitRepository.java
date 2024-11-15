package org.youcode.waitingroom.visit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.waitingroom.visit.domain.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
