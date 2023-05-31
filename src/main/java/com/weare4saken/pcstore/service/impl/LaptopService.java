package com.weare4saken.pcstore.service.impl;

import com.weare4saken.pcstore.dto.LaptopDto;
import com.weare4saken.pcstore.exception.IncorrectArgumentException;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.mapper.LaptopMapper;
import com.weare4saken.pcstore.model.Laptop;
import com.weare4saken.pcstore.repository.LaptopRepository;
import com.weare4saken.pcstore.service.ProductDtoService;
import com.weare4saken.pcstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LaptopService implements ProductDtoService<LaptopDto>, ProductService<Laptop> {

    private final LaptopRepository laptopRepository;


    @Override
    public LaptopDto addProduct(LaptopDto product) {
        log.debug("Adding laptop");
        if(validCheck(product)) throw new IncorrectArgumentException();
        Laptop laptop = LaptopMapper.INSTANCE.toEntity(product);
        log.debug("Laptop successfully add");
        return LaptopMapper.INSTANCE.toDto(laptopRepository.save(laptop));
    }

    @Override
    public LaptopDto updateProduct(String serialNumber, LaptopDto product) {
        log.debug("Updating laptop by serialNumber: {}", serialNumber);
        Laptop laptop = findProductById(serialNumber);
        laptop.setPrice(product.getPrice());
        laptop.setAmount(product.getAmount());
        laptopRepository.save(laptop);
        log.info("Details updated for monitor: {}", serialNumber);
        return LaptopMapper.INSTANCE.toDto(laptop);
    }

    @Override
    public List<LaptopDto> getAllProducts() {
        log.debug("Getting all laptops");
        return laptopRepository.findAll()
                .stream()
                .map(LaptopMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LaptopDto getProduct(String serialNumber) {
        log.debug("Getting laptop by serialNumber: {}", serialNumber);
        return LaptopMapper.INSTANCE.toDto(findProductById(serialNumber));
    }

    @Override
    public boolean validCheck(LaptopDto product) {
        log.debug("Validation check for laptop");
        return product.getSerialNumber() == null || product.getSerialNumber().isBlank()
                || product.getProducer() == null || product.getProducer().isBlank()
                || product.getAmount() == null
                || product.getPrice() == null
                || product.getSize() == null;
    }

    @Override
    public Laptop findProductById(String serialNumber) {
        log.debug("Finding laptop by serialNumber: {}", serialNumber);
        return laptopRepository.findById(serialNumber).orElseThrow(ProductNotFoundException::new);
    }

}
