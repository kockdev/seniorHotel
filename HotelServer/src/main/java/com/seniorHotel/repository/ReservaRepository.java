package com.seniorHotel.repository;
import java.util.List;
import com.seniorHotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByDataCheckinIsNull();
}
