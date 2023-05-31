package com.weare4saken.pcstore.service;

import com.weare4saken.pcstore.dto.MonitorDto;
import com.weare4saken.pcstore.enums.Diagonal;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.model.Monitor;
import com.weare4saken.pcstore.repository.MonitorRepository;
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
public class MonitorServiceTest {

    @Mock
    private MonitorRepository monitorRepository;
    @InjectMocks
    private MonitorService monitorService;

    @Test
    public void testValidCheckReturnsTrueWhenMonitorPropertiesAreNotEmpty() {
        MonitorDto monitorDto = new MonitorDto();
        monitorDto.setSerialNumber("TestSerialNumber");
        monitorDto.setProducer("TestProducer");
        monitorDto.setPrice(300.00);
        monitorDto.setAmount(20);
        monitorDto.setDiagonal(Diagonal.INCHES_27);

        boolean result = monitorService.validCheck(monitorDto);

        assertTrue(result);
    }

    @Test
    public void testFindMonitorByIdReturnsMonitorWhenFoundInRepository() {
        String serialNumber = "TestSerialNumber";
        Monitor expectedMonitor = new Monitor();

        when(monitorRepository.findById(serialNumber)).thenReturn(Optional.of(expectedMonitor));

        Monitor resultMonitor = monitorService.findProductById(serialNumber);
        assertEquals(expectedMonitor, resultMonitor);
    }

    @Test
    public void testFindMonitorByIdThrowsProductNotFoundExceptionWhenNotInRepository() {
        String serialNumber = "TestSerialNumber";

        when(monitorRepository.findById(serialNumber)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> monitorService.findProductById(serialNumber));
    }

}
