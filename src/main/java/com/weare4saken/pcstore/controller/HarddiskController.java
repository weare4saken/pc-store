package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.service.impl.HarddiskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/harddisk")
@RequiredArgsConstructor
@Tag(name = "Жесткие диски")
public class HarddiskController {

    private final HarddiskService harddiskService;


}
