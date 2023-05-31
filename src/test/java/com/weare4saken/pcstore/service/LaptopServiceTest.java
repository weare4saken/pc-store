package com.weare4saken.pcstore.service;

import com.weare4saken.pcstore.dto.LaptopDto;
import com.weare4saken.pcstore.enums.Size;
import com.weare4saken.pcstore.exception.ProductNotFoundException;
import com.weare4saken.pcstore.model.Laptop;
import com.weare4saken.pcstore.repository.LaptopRepository;
import com.weare4saken.pcstore.service.impl.LaptopService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LaptopServiceTest {

    @Mock
    private LaptopRepository laptopRepository;
    @InjectMocks
    private LaptopService laptopService;

    @Test
    public void testValidCheckReturnsTrueWhenLaptopPropertiesAreNotEmpty() {
        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setSerialNumber("TestSerialNumber");
        laptopDto.setProducer("TestProducer");
        laptopDto.setPrice(300.00);
        laptopDto.setAmount(20);
        laptopDto.setSize(Size.INCHES_17);

        boolean result = laptopService.validCheck(laptopDto);

        assertTrue(result);
    }

    @Test
    public void testFindLaptopByIdReturnsLaptopWhenFoundInRepository() {
        String serialNumber = "TestSerialNumber";
        Laptop expectedLaptop = new Laptop();

        when(laptopRepository.findById(serialNumber)).thenReturn(Optional.of(expectedLaptop));

        Laptop resultLaptop = laptopService.findProductById(serialNumber);
        assertEquals(expectedLaptop, resultLaptop);
    }

    @Test
    public void testFindLaptopByIdThrowsProductNotFoundExceptionWhenNotInRepository() {
        String serialNumber = "TestSerialNumber";

        when(laptopRepository.findById(serialNumber)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> laptopService.findProductById(serialNumber));
    }

}
