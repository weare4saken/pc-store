package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.dto.LaptopDto;
import com.weare4saken.pcstore.service.impl.LaptopService;
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
@RequestMapping("/laptop")
@RequiredArgsConstructor
@Tag(name = "Ноутбуки")
public class LaptopController {

    private final LaptopService laptopService;

    @Operation(
            summary = "Добавление нового ноутбука", tags = "Ноутбуки",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LaptopDto.class))})
            }
    )
    @PostMapping
    public ResponseEntity<LaptopDto> addLaptop(@RequestBody LaptopDto laptopDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laptopService.addProduct(laptopDto));
    }

    @Operation(
            summary = "Редактирование сведений о ноутбуке", tags = "Ноутбуки",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LaptopDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PatchMapping("/{serialNumber}")
    public ResponseEntity<LaptopDto> updateLaptop(@PathVariable("serialNumber") String serialNumber,
                                                  @RequestBody LaptopDto laptopDto) {
        return ResponseEntity.ok(laptopService.updateProduct(serialNumber, laptopDto));
    }

    @Operation(summary = "Получить все ноутбуки", tags = "Ноутбуки")
    @GetMapping
    public ResponseEntity<List<LaptopDto>> getAllLaptops() {
        return ResponseEntity.ok(laptopService.getAllProducts());
    }

    @Operation(
            summary = "Получение ноутбука по идентификатору", tags = "Ноутбуки",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LaptopDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @GetMapping("/{serialNumber}")
    public ResponseEntity<LaptopDto> getLaptop(@PathVariable("serialNumber") String serialNumber) {
        return ResponseEntity.ok(laptopService.getProduct(serialNumber));
    }

}
