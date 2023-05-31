package com.weare4saken.pcstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weare4saken.pcstore.dto.PcDto;
import com.weare4saken.pcstore.enums.FormFactor;
import com.weare4saken.pcstore.model.Pc;
import com.weare4saken.pcstore.repository.PcRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PcControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PcRepository pcRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Pc pc = new Pc();
    private final PcDto pcDto = new PcDto();

    @BeforeEach
    void setUp() {
        pc.setSerialNumber("TestSerialNumber1");
        pc.setProducer("TestProducer1");
        pc.setPrice(100.00);
        pc.setAmount(20);
        pc.setFormFactor(FormFactor.NETTOP);
        pcRepository.save(pc);
    }

    @AfterEach
    void cleanUp() {
        pcRepository.delete(pc);
    }

    @Test
    public void testAddPcReturnCorrectAddedPcFromDatabase() throws Exception {
        pcDto.setSerialNumber("TestSerialNumber2");
        pcDto.setProducer("TestProducer2");
        pcDto.setPrice(1000.00);
        pcDto.setAmount(10);
        pcDto.setFormFactor(FormFactor.DESKTOP);

        mockMvc.perform(post("/pc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pcDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value(pcDto.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(pcDto.getProducer()))
                .andExpect(jsonPath("$.price").value(pcDto.getPrice()))
                .andExpect(jsonPath("$.amount").value(pcDto.getAmount()));
    }

    @Test
    public void testUpdatePcReturnsUpdatedPc() throws Exception {
        Double newPrice = 200.00;
        Integer newAmount = 30;
        pc.setPrice(newPrice);
        pc.setAmount(newAmount);
        pcRepository.save(pc);

        mockMvc.perform(patch("/pc/{serialNumber}", pc.getSerialNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(pc.getPrice()))
                .andExpect(jsonPath("$.amount").value(pc.getAmount()));
    }

    @Test
    public void testGetAllPcReturnsCorrectListOfPcFromDatabase() throws Exception {
        mockMvc.perform(get("/pc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetPcReturnsCorrectPcFromDatabase() throws Exception {
        mockMvc.perform(get("/pc/{serialNumber}", pc.getSerialNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(pc.getSerialNumber()))
                .andExpect(jsonPath("$.producer").value(pc.getProducer()))
                .andExpect(jsonPath("$.price").value(pc.getPrice()))
                .andExpect(jsonPath("$.amount").value(pc.getAmount()));
    }

}
