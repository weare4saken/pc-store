package com.weare4saken.pcstore.service.impl;

import com.weare4saken.pcstore.dto.MonitorDto;
import com.weare4saken.pcstore.exception.IncorrectArgumentException;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.mapper.MonitorMapper;
import com.weare4saken.pcstore.model.Monitor;
import com.weare4saken.pcstore.repository.MonitorRepository;
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
public class MonitorService implements ProductDtoService<MonitorDto>, ProductService<Monitor> {

    private final MonitorRepository monitorRepository;


    @Override
    public MonitorDto addProduct(MonitorDto product) {
        log.debug("Adding monitor");
        if(!validCheck(product)) throw new IncorrectArgumentException();
        Monitor monitor = MonitorMapper.INSTANCE.toEntity(product);
        log.debug("Monitor successfully add");
        return MonitorMapper.INSTANCE.toDto(monitorRepository.save(monitor));
    }

    @Override
    public MonitorDto updateProduct(String serialNumber, MonitorDto product) {
        log.debug("Updating PC by serialNumber: {}", serialNumber);
        Monitor monitor = findProductById(serialNumber);
        monitor.setPrice(product.getPrice());
        monitor.setAmount(product.getAmount());
        monitorRepository.save(monitor);
        log.info("Details updated for monitor: {}", serialNumber);
        return MonitorMapper.INSTANCE.toDto(monitor);
    }

    @Override
    public List<MonitorDto> getAllProducts() {
        log.debug("Getting all monitors");
        return monitorRepository.findAll()
                .stream()
                .map(MonitorMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MonitorDto getProduct(String serialNumber) {
        log.debug("Getting monitor by serialNumber: {}", serialNumber);
        return MonitorMapper.INSTANCE.toDto(findProductById(serialNumber));
    }

    @Override
    public boolean validCheck(MonitorDto product) {
        log.debug("Validation check for monitor");
        return product.getSerialNumber() != null && !product.getSerialNumber().isBlank()
                && product.getProducer() != null && !product.getProducer().isBlank()
                && product.getAmount() != null
                && product.getPrice() != null
                && product.getDiagonal() != null;
    }

    @Override
    public Monitor findProductById(String serialNumber) {
        log.debug("Finding monitor by serialNumber: {}", serialNumber);
        return monitorRepository.findById(serialNumber).orElseThrow(ProductNotFoundException::new);
    }

}
