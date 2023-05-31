package com.weare4saken.pcstore.service;

import com.weare4saken.pcstore.dto.PcDto;
import com.weare4saken.pcstore.enums.FormFactor;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.model.Pc;
import com.weare4saken.pcstore.repository.PcRepository;
import com.weare4saken.pcstore.service.impl.PcService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PcServiceTest {

    @Mock
    private PcRepository pcRepository;
    @InjectMocks
    private PcService pcService;

    @Test
    public void testValidCheckReturnsTrueWhenPcPropertiesAreNotEmpty() {
        PcDto pcDto = new PcDto();
        pcDto.setSerialNumber("TestSerialNumber");
        pcDto.setProducer("TestProducer");
        pcDto.setPrice(300.00);
        pcDto.setAmount(20);
        pcDto.setFormFactor(FormFactor.MONOBLOCK);

        boolean result = pcService.validCheck(pcDto);

        assertTrue(result);
    }

    @Test
    public void testFindPcByIdReturnsPcWhenFoundInRepository() {
        String serialNumber = "TestSerialNumber";
        Pc expectedPc = new Pc();

        when(pcRepository.findById(serialNumber)).thenReturn(Optional.of(expectedPc));

        Pc resultPc = pcService.findProductById(serialNumber);
        assertEquals(expectedPc, resultPc);
    }

    @Test
    public void testFindPcByIdThrowsProductNotFoundExceptionWhenNotInRepository() {
        String serialNumber = "TestSerialNumber";

        when(pcRepository.findById(serialNumber)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> pcService.findProductById(serialNumber));
    }

}
