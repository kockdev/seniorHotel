package com.seniorHotel.service;

import com.seniorHotel.model.Hospede;
import com.seniorHotel.model.Reserva;
import com.seniorHotel.repository.HospedeRepository;
import com.seniorHotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Hospede> buscarHospedes() {
        return hospedeRepository.findAll();
    }

    public List<Hospede> buscarHospedesNoHotel() {
        return hospedeRepository.findByNoHotel(true);
    }

    public List<Hospede> buscarPorDocumento(String documento) {
        return hospedeRepository.findByDocumento(documento);
    }

    public List<Hospede> buscarPorTelefone(String telefone) {
        return hospedeRepository.findByTelefone(telefone);
    }

    public List<Hospede> buscarPorNome(String nome) {
        return hospedeRepository.findByNomeContaining(nome);
    }

    public List<Hospede> buscarHospedesSemCheckin() {
        // Buscar reservas que ainda não têm check-in
        List<Reserva> reservasSemCheckin = reservaRepository.findByDataCheckinIsNull();

        // Coletar os IDs dos hóspedes dessas reservas
        List<Long> hospedeIds = reservasSemCheckin.stream()
                .map(Reserva::getHospedeId)
                .collect(Collectors.toList());

        // Retornar a lista de hóspedes com base nos IDs coletados
        return hospedeRepository.findAllById(hospedeIds);
    }


    public void excluirHospede(Long id) {
        hospedeRepository.deleteById(id);
    }

    public Hospede atualizarStatusHospedeNoHotel(Long idHospede, boolean noHotel) {
        Hospede hospede = hospedeRepository.findById(idHospede)
                .orElseThrow(() -> new RuntimeException("Hóspede não encontrado"));
        hospede.setNoHotel(noHotel);
        return hospedeRepository.save(hospede);
    }

    public Hospede salvarHospede(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }
}
