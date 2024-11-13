package org.youcode.waitingroom.common.application.mapper;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface GenericMapper<Entity, Request, Response> {
    Entity toEntity(Request request);
    Response toDto(Entity entity);
}
