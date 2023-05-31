package com.weare4saken.pcstore.service.impl;

import com.weare4saken.pcstore.dto.HarddiskDto;
import com.weare4saken.pcstore.exception.IncorrectArgumentException;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.mapper.HarddiskMapper;
import com.weare4saken.pcstore.model.Harddisk;
import com.weare4saken.pcstore.repository.HarddiskRepository;
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
public class HarddiskService implements ProductDtoService<HarddiskDto>, ProductService<Harddisk>{

    private final HarddiskRepository harddiskRepository;


    @Override
    public HarddiskDto addProduct(HarddiskDto product) {
        log.debug("Adding harddisk");
        if(!validCheck(product)) throw new IncorrectArgumentException();
        Harddisk harddisk = HarddiskMapper.INSTANCE.toEntity(product);
        log.debug("Harddisk successfully add");
        return HarddiskMapper.INSTANCE.toDto(harddiskRepository.save(harddisk));
    }

    @Override
    public HarddiskDto updateProduct(String serialNumber, HarddiskDto product) {
        log.debug("Updating harddisk by serialNumber: {}", serialNumber);
        Harddisk harddisk = findProductById(serialNumber);
        harddisk.setPrice(product.getPrice());
        harddisk.setAmount(product.getAmount());
        harddiskRepository.save(harddisk);
        log.info("Details updated for harddisk: {}", serialNumber);
        return HarddiskMapper.INSTANCE.toDto(harddisk);
    }

    @Override
    public List<HarddiskDto> getAllProducts() {
        log.debug("Getting all harddisks");
        return harddiskRepository.findAll()
                .stream()
                .map(HarddiskMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HarddiskDto getProduct(String serialNumber) {
        log.debug("Getting harddisk by serialNumber: {}", serialNumber);
        return HarddiskMapper.INSTANCE.toDto(findProductById(serialNumber));
    }

    @Override
    public boolean validCheck(HarddiskDto product) {
        log.debug("Validation check for harddisk");
        return product.getSerialNumber() != null && !product.getSerialNumber().isBlank()
                && product.getProducer() != null && !product.getProducer().isBlank()
                && product.getAmount() != null
                && product.getPrice() != null
                && product.getCapacity() != null;
    }

    @Override
    public Harddisk findProductById(String serialNumber) {
        log.debug("Finding harddisk by serialNumber: {}", serialNumber);
        return harddiskRepository.findById(serialNumber).orElseThrow(ProductNotFoundException::new);
    }

}
