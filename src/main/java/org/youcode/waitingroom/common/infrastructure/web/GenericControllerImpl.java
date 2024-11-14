package org.youcode.waitingroom.common.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
import org.youcode.waitingroom.common.application.service.GenericService;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class GenericControllerImpl<T, ID, RequestDto, ResponseDto> implements GenericController<ID, RequestDto, ResponseDto>{

    private final GenericService<T, ID, RequestDto, ResponseDto> service;

    @Override
    public ResponseEntity<PagedResponse<ResponseDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<ResponseDto> response = service.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ResponseDto> getById(ID id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> create(RequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> update(ID id, RequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(ID id) {
        return null;
    }
}
