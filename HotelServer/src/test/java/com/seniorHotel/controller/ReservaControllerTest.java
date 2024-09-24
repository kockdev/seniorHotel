package com.seniorHotel.controller;

import com.seniorHotel.model.Reserva;
import com.seniorHotel.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @Test
    public void testCriarReserva() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setDtReservaInicio(LocalDateTime.now());
        reserva.setDtReservaFim(LocalDateTime.now().plusDays(3));

        when(reservaService.criarReserva(any(Reserva.class))).thenReturn(reserva);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservas")
                        .content("{\"hospedeId\": 1, \"dtReservaInicio\": \"2024-09-20T10:00:00\", \"dtReservaFim\": \"2024-09-23T10:00:00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(reservaService, times(1)).criarReserva(any(Reserva.class));
    }

    @Test
    public void testRealizarCheckin() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setDataCheckin(LocalDateTime.now());

        when(reservaService.realizarCheckin(1L)).thenReturn(reserva);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservas/1/checkin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dataCheckin").isNotEmpty());

        verify(reservaService, times(1)).realizarCheckin(1L);
    }

    @Test
    public void testRealizarCheckout() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setDataCheckout(LocalDateTime.now());

        when(reservaService.realizarCheckout(1L)).thenReturn(reserva);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservas/1/checkout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dataCheckout").isNotEmpty());

        verify(reservaService, times(1)).realizarCheckout(1L);
    }

    @Test
    public void testBuscarReservaId() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId(1L);

        when(reservaService.buscarReservaId(1L)).thenReturn(Optional.of(reserva));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(reservaService, times(1)).buscarReservaId(1L);
    }
}
