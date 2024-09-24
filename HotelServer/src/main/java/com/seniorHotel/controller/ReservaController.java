package com.seniorHotel.controller;

import com.seniorHotel.model.Hospede;
import com.seniorHotel.model.Reserva;
import com.seniorHotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.criarReserva(reserva);
        return ResponseEntity.ok(novaReserva);
    }

    @PostMapping("/{id}/checkin")
    public ResponseEntity<Reserva> realizarCheckin(@PathVariable Long id) {
        Reserva reserva = reservaService.realizarCheckin(id);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<Reserva> realizarCheckout(@PathVariable Long id) {
        Reserva reserva = reservaService.realizarCheckout(id);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.buscarReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaId(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.buscarReservaId(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva reservaAtualizada = reservaService.atualizarReserva(id, reserva);
        return ResponseEntity.ok(reservaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        reservaService.deletarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
