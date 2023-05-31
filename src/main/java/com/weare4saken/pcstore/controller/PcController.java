package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.dto.PcDto;
import com.weare4saken.pcstore.service.impl.PcService;
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
@RequestMapping("/pc")
@RequiredArgsConstructor
@Tag(name = "Настольные компьютеры")
public class PcController {

    private final PcService pcService;


    @Operation(
            summary = "Добавление нового компьютера", tags = "Настольные компьютеры",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PcDto.class))})
            }
    )
    @PostMapping
    public ResponseEntity<PcDto> addPc(@RequestBody PcDto pcDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pcService.addProduct(pcDto));
    }

    @Operation(
            summary = "Редактирование сведений о компьютере", tags = "Настольные компьютеры",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PcDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PatchMapping("/{serialNumber}")
    public ResponseEntity<PcDto> updatePc(@PathVariable("serialNumber") String serialNumber,
                                          @RequestBody PcDto pcDto) {
        return ResponseEntity.ok(pcService.updateProduct(serialNumber, pcDto));
    }

    @Operation(summary = "Получить все компьютеры", tags = "Настольные компьютеры")
    @GetMapping
    public ResponseEntity<List<PcDto>> getAllPc() {
        return ResponseEntity.ok(pcService.getAllProducts());
    }

    @Operation(
            summary = "Получение компьютера по идентификатору", tags = "Настольные компьютеры",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PcDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @GetMapping("/{serialNumber}")
    public ResponseEntity<PcDto> getPc(@PathVariable("serialNumber") String serialNumber) {
        return ResponseEntity.ok(pcService.getProduct(serialNumber));
    }


}
