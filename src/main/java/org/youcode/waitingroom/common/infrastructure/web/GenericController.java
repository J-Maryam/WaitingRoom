package org.youcode.waitingroom.common.infrastructure.web;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.waitingroom.common.application.dto.PagedResponse;


public interface GenericController<ID, RequestDto, ResponseDto> {
    @GetMapping
    ResponseEntity<PagedResponse<ResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto> getById(@PathVariable ID id);

    @PostMapping
    ResponseEntity<ResponseDto> create(@RequestBody RequestDto requestDto);

    @PutMapping("/{id}")
    ResponseEntity<ResponseDto> update(@PathVariable ID id, @RequestBody RequestDto requestDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);
}
