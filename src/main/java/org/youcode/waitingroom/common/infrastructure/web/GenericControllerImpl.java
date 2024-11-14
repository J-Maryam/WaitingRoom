package org.youcode.waitingroom.common.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
import org.youcode.waitingroom.common.application.service.GenericService;

@RequiredArgsConstructor
public abstract class GenericControllerImpl<T, ID, RequestDto, ResponseDto> implements GenericController<ID, RequestDto, ResponseDto> {

    private final GenericService<T, ID, RequestDto, ResponseDto> service;

    @Override
    public ResponseEntity<PagedResponse<ResponseDto>> getAll(Pageable pageable) {
        PagedResponse<ResponseDto> response = service.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ResponseDto> getById(ID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<ResponseDto> create(RequestDto requestDto) {
        return ResponseEntity.ok(service.create(requestDto));
    }

    @Override
    public ResponseEntity<ResponseDto> update(ID id, RequestDto requestDto) {
        return ResponseEntity.ok(service.update(id, requestDto));
    }

    @Override
    public ResponseEntity<Void> delete(ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}