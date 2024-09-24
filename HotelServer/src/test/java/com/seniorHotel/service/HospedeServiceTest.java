package com.seniorHotel.service;

import com.seniorHotel.model.Hospede;
import com.seniorHotel.repository.HospedeRepository;
import com.seniorHotel.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HospedeServiceTest {

    @Autowired
    private HospedeService hospedeService;

    @MockBean
    private HospedeRepository hospedeRepository;

    @MockBean
    private ReservaRepository reservaRepository;

    @Test
    public void testBuscarHospedes() {
        Hospede hospede1 = new Hospede();
        hospede1.setId(1L);
        hospede1.setNome("João");

        Hospede hospede2 = new Hospede();
        hospede2.setId(2L);
        hospede2.setNome("Maria");

        when(hospedeRepository.findAll()).thenReturn(Arrays.asList(hospede1, hospede2));

        List<Hospede> hospedes = hospedeService.buscarHospedes();

        assertEquals(2, hospedes.size());
        verify(hospedeRepository, times(1)).findAll();
    }

    @Test
    public void testAtualizarStatusHospedeNoHotel() {
        Hospede hospede = new Hospede();
        hospede.setId(1L);
        hospede.setNome("João");
        hospede.setNoHotel(false);

        when(hospedeRepository.findById(1L)).thenReturn(Optional.of(hospede));
        when(hospedeRepository.save(any(Hospede.class))).thenReturn(hospede);

        hospedeService.atualizarStatusHospedeNoHotel(1L, true);

        assertTrue(hospede.isNoHotel());
        verify(hospedeRepository, times(1)).save(hospede);
    }
}
