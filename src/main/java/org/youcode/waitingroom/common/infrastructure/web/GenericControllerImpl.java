package org.youcode.waitingroom.common.infrastructure.web;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.youcode.waitingroom.common.application.dto.PagedResponse;
import org.youcode.waitingroom.common.application.service.GenericService;
import org.youcode.waitingroom.waitingRoom.application.dto.ApiResponse;

public abstract class GenericControllerImpl<T, ID, RequestDto, ResponseDto> implements GenericController<ID, RequestDto, ResponseDto> {

    protected GenericService<T, ID, RequestDto, ResponseDto> service;

    public GenericControllerImpl(GenericService<T, ID, RequestDto, ResponseDto> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ApiResponse<PagedResponse<ResponseDto>>> getAll(Pageable pageable) {
        PagedResponse<ResponseDto> response = service.getAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(response, "Data retrieved successfully"));
    }

    @Override
    public ResponseEntity<ApiResponse<ResponseDto>> getById(ID id) {
        ResponseDto dto = service.getById(id);
        return ResponseEntity.ok(ApiResponse.success(dto, "Item retrieved successfully"));
    }

    @Override
    public ResponseEntity<ApiResponse<ResponseDto>> create(RequestDto requestDto) {
        ResponseDto createdDto = service.create(requestDto);
        return ResponseEntity.ok(ApiResponse.success(createdDto, "Item created successfully"));
    }

    @Override
    public ResponseEntity<ApiResponse<ResponseDto>> update(ID id, RequestDto requestDto) {
        ResponseDto updatedDto = service.update(id, requestDto);
        return ResponseEntity.ok(ApiResponse.success(updatedDto, "Item updated successfully"));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(ID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Item deleted successfully"));
    }
}