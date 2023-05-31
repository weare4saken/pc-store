package com.weare4saken.pcstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weare4saken.pcstore.dto.LaptopDto;
import com.weare4saken.pcstore.enums.Size;
import com.weare4saken.pcstore.model.Laptop;
import com.weare4saken.pcstore.repository.LaptopRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LaptopControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Laptop laptop = new Laptop();
    private final LaptopDto laptopDto = new LaptopDto();

    @BeforeEach
    void setUp() {
        laptop.setSerialNumber("TestSerialNumber1");
        laptop.setProducer("TestProducer1");
        laptop.setPrice(100.00);
        laptop.setAmount(20);
        laptop.setSize(Size.INCHES_17);
        laptopRepository.save(laptop);
    }

    @AfterEach
    void cleanUp() {
        laptopRepository.delete(laptop);
    }

    @Test
    public void testAddLaptopReturnCorrectAddedLaptopFromDatabase() throws Exception {
        laptopDto.setSerialNumber("TestSerialNumber2");
        laptopDto.setProducer("TestProducer2");
        laptopDto.setPrice(1000.00);
        laptopDto.setAmount(10);
        laptopDto.setSize(Size.INCHES_14);

        mockMvc.perform(post("/laptop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptopDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value(laptopDto.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(laptopDto.getProducer()))
                .andExpect(jsonPath("$.price").value(laptopDto.getPrice()))
                .andExpect(jsonPath("$.amount").value(laptopDto.getAmount()));
    }

    @Test
    public void testUpdateLaptopReturnsUpdatedLaptop() throws Exception {
        Double newPrice = 200.00;
        Integer newAmount = 30;
        laptop.setPrice(newPrice);
        laptop.setAmount(newAmount);
        laptopRepository.save(laptop);

        mockMvc.perform(patch("/laptop/{serialNumber}", laptop.getSerialNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(laptop.getPrice()))
                .andExpect(jsonPath("$.amount").value(laptop.getAmount()));
    }

    @Test
    public void testGetAllLaptopReturnsCorrectListOfLaptopFromDatabase() throws Exception {
        mockMvc.perform(get("/laptop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetLaptopReturnsCorrectLaptopFromDatabase() throws Exception {
        mockMvc.perform(get("/laptop/{serialNumber}", laptop.getSerialNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(laptop.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(laptop.getProducer()))
                .andExpect(jsonPath("$.price").value(laptop.getPrice()))
                .andExpect(jsonPath("$.amount").value(laptop.getAmount()));
    }

}
