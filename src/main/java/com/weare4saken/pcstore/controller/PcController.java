package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.service.impl.PcService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pc")
@RequiredArgsConstructor
@Tag(name = "Настольные компьютеры")
public class PcController {

    private final PcService pcService;


}
