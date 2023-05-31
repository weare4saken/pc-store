package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.dto.HarddiskDto;
import com.weare4saken.pcstore.service.impl.HarddiskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/harddisk")
@RequiredArgsConstructor
@Tag(name = "Жесткие диски")
public class HarddiskController {

    private final HarddiskService harddiskService;

    @Operation(
            summary = "Добавление нового жесткого диска", tags = "Жесткие диски",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HarddiskDto.class))})
            }
    )
    @PostMapping
    public ResponseEntity<HarddiskDto> addHarddisk(@RequestBody HarddiskDto harddiskDto) {
        return ResponseEntity.ok(harddiskService.addProduct(harddiskDto));
    }

    @Operation(
            summary = "Редактирование сведений о жестком диске", tags = "Жесткие диски",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HarddiskDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PatchMapping("/{serialNumber}")
    public ResponseEntity<HarddiskDto> updateHarddisk(@PathVariable("serialNumber") String serialNumber,
                                                    @RequestBody HarddiskDto harddiskDto) {
        return ResponseEntity.ok(harddiskService.updateProduct(serialNumber, harddiskDto));
    }

    @Operation(summary = "Получить все жесткие диски", tags = "Жесткие диски")
    @GetMapping
    public ResponseEntity<List<HarddiskDto>> getAllLaptops() {
        return ResponseEntity.ok(harddiskService.getAllProducts());
    }

    @Operation(
            summary = "Получение жесткого диска по идентификатору", tags = "Жесткие диски",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HarddiskDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @GetMapping("/{serialNumber}")
    public ResponseEntity<HarddiskDto> getLaptop(@PathVariable("serialNumber") String serialNumber) {
        return ResponseEntity.ok(harddiskService.getProduct(serialNumber));
    }

}
