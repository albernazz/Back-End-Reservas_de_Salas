package com.example.reservaDeSalas.controller;

import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<Reserva>> listar(){
        List<Reserva> lista = service.listarReservas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id){
        Reserva reserva = service.buscarPorId(id);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody @Valid Reserva reserva){
        service.criarReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva Criada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Reserva atualizacaoReserva){
        service.atualizarReserva(id, atualizacaoReserva);
        return ResponseEntity.ok("Reserva atualizada !!");
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id){
        service.cancelarReserva(id);
        return ResponseEntity.ok("Reserva cancelada !!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        service.deletarReserva(id);
        return ResponseEntity.ok("Reserva deletada !!");
    }
}
