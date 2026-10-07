package com.example.reservaDeSalas.controller;

import com.example.reservaDeSalas.model.Sala;
import com.example.reservaDeSalas.service.SalaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public ResponseEntity<List<Sala>> listar(){
        List<Sala> lista = service.listarSalas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id){
        Sala sala = service.buscarPorId(id);
        return ResponseEntity.ok(sala);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody @Valid Sala sala){
        service.criarSala(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sala criada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid Sala sala){
        service.atualizarSala(id, sala);
        return ResponseEntity.ok("Sala atualizada !!");
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<String> desativar(@PathVariable Long id) {
        service.desativarSala(id);
        return ResponseEntity.ok("Sala desativada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletarSala(id);
        return ResponseEntity.ok("Sala deletada com sucesso!");
    }

}
