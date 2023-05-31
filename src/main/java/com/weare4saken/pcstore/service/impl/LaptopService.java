package com.weare4saken.pcstore.service.impl;

import com.weare4saken.pcstore.repository.LaptopRepository;
import com.weare4saken.pcstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LaptopService implements ProductService {

    private final LaptopRepository laptopRepository;


}
