package com.seniorHotel.controller;

import com.seniorHotel.model.Hospede;
import com.seniorHotel.service.HospedeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HospedeController.class)
public class HospedeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospedeService hospedeService;

    @Test
    public void testListarHospedes() throws Exception {
        // Mocking the service response
        Hospede hospede1 = new Hospede();
        hospede1.setId(1L);
        hospede1.setNome("João");
        hospede1.setDocumento("123456789");

        Hospede hospede2 = new Hospede();
        hospede2.setId(2L);
        hospede2.setNome("Maria");
        hospede2.setDocumento("987654321");

        when(hospedeService.buscarHospedes()).thenReturn(Arrays.asList(hospede1, hospede2));

        // Performing a GET request and expecting JSON response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hospedes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));

        verify(hospedeService, times(1)).buscarHospedes();
    }

    @Test
    public void testCadastrarHospede() throws Exception {
        Hospede hospede = new Hospede();
        hospede.setId(1L);
        hospede.setNome("João");
        hospede.setDocumento("123456789");

        when(hospedeService.salvarHospede(any(Hospede.class))).thenReturn(hospede);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/hospedes")
                        .content("{\"nome\": \"João\", \"documento\": \"123456789\", \"telefone\": \"999999999\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.documento").value("123456789"));

        verify(hospedeService, times(1)).salvarHospede(any(Hospede.class));
    }
}
