package org.youcode.waitingroom.common.infrastructure.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.waitingroom.common.application.dto.PagedResponse;

public interface GenericController<ID, RequestDto, ResponseDto> {
    @GetMapping
    ResponseEntity<PagedResponse<ResponseDto>> getAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto> getById(@PathVariable ID id);

    @PostMapping
    ResponseEntity<ResponseDto> create(@RequestBody @Valid RequestDto requestDto);

    @PutMapping("/{id}")
    ResponseEntity<ResponseDto> update(@PathVariable ID id, @RequestBody @Valid RequestDto requestDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);
}
