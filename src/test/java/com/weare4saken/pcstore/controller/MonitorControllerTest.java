package com.weare4saken.pcstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weare4saken.pcstore.dto.MonitorDto;
import com.weare4saken.pcstore.enums.Diagonal;
import com.weare4saken.pcstore.model.Monitor;
import com.weare4saken.pcstore.repository.MonitorRepository;
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
public class MonitorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MonitorRepository monitorRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Monitor monitor = new Monitor();
    private final MonitorDto monitorDto = new MonitorDto();

    @BeforeEach
    void setUp() {
        monitor.setSerialNumber("TestSerialNumber1");
        monitor.setProducer("TestProducer1");
        monitor.setPrice(100.00);
        monitor.setAmount(20);
        monitor.setDiagonal(Diagonal.INCHES_24);
        monitorRepository.save(monitor);
    }

    @AfterEach
    void cleanUp() {
        monitorRepository.delete(monitor);
    }

    @Test
    public void testAddMonitorReturnCorrectAddedMonitorFromDatabase() throws Exception {
        monitorDto.setSerialNumber("TestSerialNumber2");
        monitorDto.setProducer("TestProducer2");
        monitorDto.setPrice(1000.00);
        monitorDto.setAmount(10);
        monitorDto.setDiagonal(Diagonal.INCHES_27);

        mockMvc.perform(post("/monitor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitorDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value(monitorDto.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(monitorDto.getProducer()))
                .andExpect(jsonPath("$.price").value(monitorDto.getPrice()))
                .andExpect(jsonPath("$.amount").value(monitorDto.getAmount()));
    }

    @Test
    public void testUpdateMonitorReturnsUpdatedMonitor() throws Exception {
        Double newPrice = 200.00;
        Integer newAmount = 30;
        monitor.setPrice(newPrice);
        monitor.setAmount(newAmount);
        monitorRepository.save(monitor);

        mockMvc.perform(patch("/monitor/{serialNumber}", monitor.getSerialNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(monitor.getPrice()))
                .andExpect(jsonPath("$.amount").value(monitor.getAmount()));
    }

    @Test
    public void testGetAllPcReturnsCorrectListOfMonitorsFromDatabase() throws Exception {
        mockMvc.perform(get("/monitor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetMonitorReturnsCorrectMonitorFromDatabase() throws Exception {
        mockMvc.perform(get("/monitor/{serialNumber}", monitor.getSerialNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(monitor.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(monitor.getProducer()))
                .andExpect(jsonPath("$.price").value(monitor.getPrice()))
                .andExpect(jsonPath("$.amount").value(monitor.getAmount()));
    }

}
