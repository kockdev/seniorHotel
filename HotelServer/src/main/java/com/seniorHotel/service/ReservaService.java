package com.seniorHotel.service;

import com.seniorHotel.model.Hospede;
import com.seniorHotel.model.Reserva;
import com.seniorHotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private HospedeService hospedeService;

    public Reserva criarReserva(Reserva reserva) {
        reserva.setDataCheckin(null);
        reserva.setDataCheckout(null);
        reserva.setValorTotal(0);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarReservas() {
        return reservaRepository.findAll();
    }


    public Optional<Reserva> buscarReservaId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva atualizarReserva(Long id, Reserva reservaAtualizada) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reservaExistente = reservaOptional.get();
            reservaExistente.setDtReservaInicio(reservaAtualizada.getDtReservaInicio());
            reservaExistente.setDtReservaFim(reservaAtualizada.getDtReservaFim());
            reservaExistente.setCarro(reservaAtualizada.isCarro());
            reservaExistente.setDataCheckin(reservaAtualizada.getDataCheckin());
            reservaExistente.setDataCheckout(reservaAtualizada.getDataCheckout());
            calcularValorTotal(reservaExistente);
            return reservaRepository.save(reservaExistente);
        } else {
            throw new RuntimeException("Reserva não encontrada");
        }
    }

    public void deletarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public Reserva realizarCheckin(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setDataCheckin(LocalDateTime.now()); // Define a data de check-in como o momento atual
        hospedeService.atualizarStatusHospedeNoHotel(reserva.getHospedeId(), true);

        return reservaRepository.save(reserva);
    }

    public Reserva realizarCheckout(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setDataCheckout(LocalDateTime.now()); // Define a data de checkout como o momento atual

        // Cálculo do valor total com base nas regras de negócio
        double valorTotal = calcularValorTotal(reserva);
        reserva.setValorTotal(valorTotal);
        hospedeService.atualizarStatusHospedeNoHotel(reserva.getHospedeId(), false);
        return reservaRepository.save(reserva);
    }

    public double calcularValorTotal(Reserva reserva) {
        LocalDateTime inicio = reserva.getDataCheckin();
        LocalDateTime fim = reserva.getDataCheckout();
        boolean usaCarro = reserva.isCarro();

        double valorTotal = 0;

        while (!inicio.isAfter(fim)) {
            DayOfWeek diaDaSemana = inicio.getDayOfWeek();

            // Verificar se é dia útil ou final de semana
            if (diaDaSemana == DayOfWeek.SATURDAY || diaDaSemana == DayOfWeek.SUNDAY) {
                valorTotal += 180; // Diária final de semana
                if (usaCarro) {
                    valorTotal += 20; // Taxa de estacionamento final de semana
                }
            } else {
                valorTotal += 120; // Diária dia útil
                if (usaCarro) {
                    valorTotal += 15; // Taxa de estacionamento dia útil
                }
            }

            // Avança para o próximo dia
            inicio = inicio.plusDays(1);
        }

        return valorTotal;
    }
}
