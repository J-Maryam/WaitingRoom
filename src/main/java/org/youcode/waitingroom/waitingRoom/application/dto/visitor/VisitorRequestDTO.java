package org.youcode.waitingroom.waitingRoom.application.dto.visitor;

import jakarta.validation.constraints.NotBlank;
import org.youcode.waitingroom.common.application.validation.annotation.Exists;
import org.youcode.waitingroom.common.application.validation.annotation.UniqueValue;
import org.youcode.waitingroom.waitingRoom.domain.entity.Visitor;

public record VisitorRequestDTO(
        @UniqueValue(entityClass = Visitor.class, fieldName = "name", message = "Visitor already exists with this name")
        @NotBlank String name
) {
}
