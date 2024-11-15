package org.youcode.waitingroom.visit.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.waitingroom.common.application.mapper.GenericMapper;
import org.youcode.waitingroom.visit.application.dto.VisitRequestDTO;
import org.youcode.waitingroom.visit.application.dto.VisitResponseDTO;
import org.youcode.waitingroom.visit.domain.Visit;

@Mapper(componentModel = "spring")
public interface VisitMapper extends GenericMapper<Visit, VisitRequestDTO, VisitResponseDTO> {
}
