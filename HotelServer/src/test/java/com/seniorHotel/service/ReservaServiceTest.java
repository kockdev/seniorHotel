package com.seniorHotel.service;

import com.seniorHotel.model.Reserva;
import com.seniorHotel.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservaServiceTest {

    @Autowired
    private ReservaService reservaService;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private HospedeService hospedeService;

    @Test
    public void testCriarReserva() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setDtReservaInicio(LocalDateTime.now());
        reserva.setDtReservaFim(LocalDateTime.now().plusDays(3));

        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva novaReserva = reservaService.criarReserva(reserva);

        assertEquals(1L, novaReserva.getId());
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    public void testRealizarCheckin() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setHospedeId(1L);

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaComCheckin = reservaService.realizarCheckin(1L);

        assertTrue(reservaComCheckin.getDataCheckin() != null);
        verify(hospedeService, times(1)).atualizarStatusHospedeNoHotel(1L, true);
        verify(reservaRepository, times(1)).save(reservaComCheckin);
    }

    @Test
    public void testRealizarCheckout() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setHospedeId(1L);
        reserva.setDtReservaInicio(LocalDateTime.now());
        reserva.setDtReservaFim(LocalDateTime.now().plusDays(3));
        reserva.setDataCheckin(LocalDateTime.now().minusDays(2));
        reserva.setCarro(true);

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaComCheckout = reservaService.realizarCheckout(1L);

        assertNotNull(reservaComCheckout.getDataCheckout());
        assertEquals(470, reservaComCheckout.getValorTotal());
        verify(hospedeService, times(1)).atualizarStatusHospedeNoHotel(1L, false);
        verify(reservaRepository, times(1)).save(reservaComCheckout);
    }

    @Test
    public void testCalcularValorTotal() {
        Reserva reserva = new Reserva();
        reserva.setDataCheckin(LocalDateTime.of(2024, 9, 20, 12, 0));
        reserva.setDataCheckout(LocalDateTime.of(2024, 9, 23, 12, 0));
        reserva.setCarro(true);

        double valorTotal = reservaService.calcularValorTotal(reserva);

        assertEquals(670, valorTotal);
    }
}
