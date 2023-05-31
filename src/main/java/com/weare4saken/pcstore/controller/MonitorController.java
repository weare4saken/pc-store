package com.weare4saken.pcstore.controller;

import com.weare4saken.pcstore.service.impl.MonitorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
@Tag(name = "Мониторы")
public class MonitorController {

    private final MonitorService monitorService;


}
