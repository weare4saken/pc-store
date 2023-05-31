package com.weare4saken.pcstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weare4saken.pcstore.dto.HarddiskDto;
import com.weare4saken.pcstore.enums.Capacity;
import com.weare4saken.pcstore.model.Harddisk;
import com.weare4saken.pcstore.repository.HarddiskRepository;
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
public class HarddiskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HarddiskRepository harddiskRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Harddisk harddisk = new Harddisk();
    private final HarddiskDto harddiskDto = new HarddiskDto();

    @BeforeEach
    void setUp() {
        harddisk.setSerialNumber("TestSerialNumber1");
        harddisk.setProducer("TestProducer1");
        harddisk.setPrice(100.00);
        harddisk.setAmount(20);
        harddisk.setCapacity(Capacity.GB_500);
        harddiskRepository.save(harddisk);
    }

    @AfterEach
    void cleanUp() {
        harddiskRepository.delete(harddisk);
    }

    @Test
    public void testAddHarddiskReturnCorrectAddedHarddiskFromDatabase() throws Exception {
        harddiskDto.setSerialNumber("TestSerialNumber2");
        harddiskDto.setProducer("TestProducer2");
        harddiskDto.setPrice(1000.00);
        harddiskDto.setAmount(10);
        harddiskDto.setCapacity(Capacity.TB_1);

        mockMvc.perform(post("/harddisk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(harddiskDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value(harddiskDto.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(harddiskDto.getProducer()))
                .andExpect(jsonPath("$.price").value(harddiskDto.getPrice()))
                .andExpect(jsonPath("$.amount").value(harddiskDto.getAmount()));
    }

    @Test
    public void testUpdateHarddiskReturnsUpdatedHarddisk() throws Exception {
        Double newPrice = 200.00;
        Integer newAmount = 30;
        harddisk.setPrice(newPrice);
        harddisk.setAmount(newAmount);
        harddiskRepository.save(harddisk);

        mockMvc.perform(patch("/harddisk/{serialNumber}", harddisk.getSerialNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(harddisk)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(harddisk.getPrice()))
                .andExpect(jsonPath("$.amount").value(harddisk.getAmount()));
    }

    @Test
    public void testGetAllPcReturnsCorrectListOfHarddisksFromDatabase() throws Exception {
        mockMvc.perform(get("/harddisk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetHarddiskReturnsCorrectHarddiskFromDatabase() throws Exception {
        mockMvc.perform(get("/harddisk/{serialNumber}", harddisk.getSerialNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(harddisk.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(harddisk.getProducer()))
                .andExpect(jsonPath("$.price").value(harddisk.getPrice()))
                .andExpect(jsonPath("$.amount").value(harddisk.getAmount()));
    }

}
