package com.seniorHotel.repository;
import org.springframework.data.jpa.repository.Query;
import com.seniorHotel.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    List<Hospede> findByNomeContaining(String nome);
    List<Hospede> findByDocumento(String documento);
    List<Hospede> findByTelefone(String telefone);
    List<Hospede> findByNoHotel(boolean noHotel);

}
