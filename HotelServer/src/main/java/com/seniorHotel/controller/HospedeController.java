package com.seniorHotel.controller;

import com.seniorHotel.model.Hospede;
import com.seniorHotel.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospedes")
@CrossOrigin(origins = "http://localhost:4200")
public class HospedeController {

    @Autowired
    private HospedeService hospedeService;

    @GetMapping
    public List<Hospede> listarHospedes() {
        return hospedeService.buscarHospedes();
    }

    @PostMapping
    public Hospede cadastrarHospede(@RequestBody Hospede hospede) {
        return hospedeService.salvarHospede(hospede);
    }

    @PutMapping("/{id}")
    public Hospede editarHospede(@PathVariable Long id, @RequestBody Hospede hospede) {
        hospede.setId(id);
        return hospedeService.salvarHospede(hospede);
    }

    @DeleteMapping("/{id}")
    public void excluirHospede(@PathVariable Long id) {
        hospedeService.excluirHospede(id);
    }

    @GetMapping("/buscar/nome")
    public List<Hospede> buscarPorNome(@RequestParam String nome) {
        return hospedeService.buscarPorNome(nome);
    }

    @GetMapping("/buscar/documento")
    public List<Hospede> buscarPorDocumento(@RequestParam String documento) {
        return hospedeService.buscarPorDocumento(documento);
    }

    @GetMapping("/buscar/telefone")
    public List<Hospede> buscarPorTelefone(@RequestParam String telefone) {
        return hospedeService.buscarPorTelefone(telefone);
    }

    @GetMapping("/no-hotel")
    public List<Hospede> listarHospedesNoHotel() {
        return hospedeService.buscarHospedesNoHotel();
    }

    @GetMapping("/sem-checkin")
    public List<Hospede> listarHospedesSemCheckin() {
        return hospedeService.buscarHospedesSemCheckin();
    }

}
