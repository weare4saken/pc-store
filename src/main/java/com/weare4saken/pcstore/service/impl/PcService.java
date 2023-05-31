package com.weare4saken.pcstore.service.impl;

import com.weare4saken.pcstore.dto.PcDto;
import com.weare4saken.pcstore.exception.IncorrectArgumentException;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.mapper.PcMapper;
import com.weare4saken.pcstore.model.Pc;
import com.weare4saken.pcstore.repository.PcRepository;
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
public class PcService implements ProductDtoService<PcDto>, ProductService<Pc> {

    private final PcRepository pcRepository;


    @Override
    public PcDto addProduct(PcDto product) {
        log.debug("Adding PC");
        if(!validCheck(product)) throw new IncorrectArgumentException();
        Pc pc = PcMapper.INSTANCE.toEntity(product);
        log.debug("PC successfully add");
        return PcMapper.INSTANCE.toDto(pcRepository.save(pc));
    }

    @Override
    public PcDto updateProduct(String serialNumber, PcDto product) {
        log.debug("Updating PC by serialNumber: {}", serialNumber);
        Pc pc = findProductById(serialNumber);
        pc.setPrice(product.getPrice());
        pc.setAmount(product.getAmount());
        pcRepository.save(pc);
        log.info("Details updated for PC: {}", serialNumber);
        return PcMapper.INSTANCE.toDto(pc);
    }

    @Override
    public List<PcDto> getAllProducts() {
        log.debug("Getting all PC's");
        return pcRepository.findAll()
                .stream()
                .map(PcMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PcDto getProduct(String serialNumber) {
        log.debug("Getting PC by serialNumber: {}", serialNumber);
        return PcMapper.INSTANCE.toDto(findProductById(serialNumber));
    }

    @Override
    public boolean validCheck(PcDto product) {
        log.debug("Validation check for PC");
        return product.getSerialNumber() != null && !product.getSerialNumber().isBlank()
                && product.getProducer() != null && !product.getProducer().isBlank()
                && product.getAmount() != null
                && product.getPrice() != null
                && product.getFormFactor() != null;
    }

    @Override
    public Pc findProductById(String serialNumber) {
        log.debug("Finding PC by serialNumber: {}", serialNumber);
        return pcRepository.findById(serialNumber).orElseThrow(ProductNotFoundException::new);
    }

}
