package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.service.impl.LaptopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laptop")
@RequiredArgsConstructor
@Tag(name = "Ноутбуки")
public class LaptopController {

    private final LaptopService laptopService;


}
