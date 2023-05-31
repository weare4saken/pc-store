package com.weare4saken.pcstore.service;

import com.weare4saken.pcstore.dto.HarddiskDto;
import com.weare4saken.pcstore.dto.MonitorDto;
import com.weare4saken.pcstore.enums.Capacity;
import com.weare4saken.pcstore.enums.Diagonal;
import com.weare4saken.pcstore.enums.Size;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.model.Harddisk;
import com.weare4saken.pcstore.model.Monitor;
import com.weare4saken.pcstore.repository.HarddiskRepository;
import com.weare4saken.pcstore.repository.MonitorRepository;
import com.weare4saken.pcstore.service.impl.HarddiskService;
import com.weare4saken.pcstore.service.impl.MonitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HarddiskTest {

    @Mock
    private HarddiskRepository harddiskRepository;
    @InjectMocks
    private HarddiskService harddiskService;

    @Test
    public void testValidCheckReturnsTrueWhenHarddiskPropertiesAreNotEmpty() {
        HarddiskDto harddiskDto = new HarddiskDto();
        harddiskDto.setSerialNumber("TestSerialNumber");
        harddiskDto.setProducer("TestProducer");
        harddiskDto.setPrice(300.00);
        harddiskDto.setAmount(20);
        harddiskDto.setCapacity(Capacity.GB_500);

        boolean result = harddiskService.validCheck(harddiskDto);

        assertTrue(result);
    }

    @Test
    public void testFindHarddiskByIdReturnsHarddiskWhenFoundInRepository() {
        String serialNumber = "TestSerialNumber";
        Harddisk expectedHarddisk = new Harddisk();

        when(harddiskRepository.findById(serialNumber)).thenReturn(Optional.of(expectedHarddisk));

        Harddisk resultHarddisk = harddiskService.findProductById(serialNumber);
        assertEquals(expectedHarddisk, resultHarddisk);
    }

    @Test
    public void testFindHarddiskByIdThrowsProductNotFoundExceptionWhenNotInRepository() {
        String serialNumber = "TestSerialNumber";

        when(harddiskRepository.findById(serialNumber)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> harddiskService.findProductById(serialNumber));
    }

}
