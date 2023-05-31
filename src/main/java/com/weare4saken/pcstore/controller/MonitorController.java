package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.dto.MonitorDto;
import com.weare4saken.pcstore.service.impl.MonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
@Tag(name = "Мониторы")
public class MonitorController {

    private final MonitorService monitorService;

    @Operation(
            summary = "Добавление нового монитора", tags = "Мониторы",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MonitorDto.class))})
            }
    )
    @PostMapping
    public ResponseEntity<MonitorDto> addMonitor(@RequestBody MonitorDto monitorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monitorService.addProduct(monitorDto));
    }

    @Operation(
            summary = "Редактирование сведений о мониторе", tags = "Мониторы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MonitorDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PatchMapping("/{serialNumber}")
    public ResponseEntity<MonitorDto> updateMonitor(@PathVariable("serialNumber") String serialNumber,
                                               @RequestBody MonitorDto monitorDto) {
        return ResponseEntity.ok(monitorService.updateProduct(serialNumber, monitorDto));
    }

    @Operation(summary = "Получить все мониторы", tags = "Мониторы")
    @GetMapping
    public ResponseEntity<List<MonitorDto>> getAllMonitors() {
        return ResponseEntity.ok(monitorService.getAllProducts());
    }

    @Operation(
            summary = "Получение монитора по идентификатору", tags = "Мониторы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MonitorDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @GetMapping("/{serialNumber}")
    public ResponseEntity<MonitorDto> getMonitor(@PathVariable("serialNumber") String serialNumber) {
        return ResponseEntity.ok(monitorService.getProduct(serialNumber));
    }

}
