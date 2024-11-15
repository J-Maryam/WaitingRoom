package org.youcode.waitingroom.waitingRoom.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.waitingroom.common.application.mapper.GenericMapper;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorRequestDTO;
import org.youcode.waitingroom.waitingRoom.application.dto.visitor.VisitorResponseDTO;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;

@Mapper(componentModel = "spring")
public interface VisitorMapper extends GenericMapper<Visitor, VisitorRequestDTO, VisitorResponseDTO> {
}
