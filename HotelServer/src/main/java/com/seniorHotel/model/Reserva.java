package com.seniorHotel.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hospedeId;
    private LocalDateTime dtReservaInicio;
    private LocalDateTime dtReservaFim;
    private LocalDateTime dataCheckin;
    private LocalDateTime dataCheckout;
    private boolean carro;
    private double valorTotal;

}
