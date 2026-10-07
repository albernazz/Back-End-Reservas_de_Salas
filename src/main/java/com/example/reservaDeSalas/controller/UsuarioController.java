package com.example.reservaDeSalas.controller;

import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.model.Usuario;
import com.example.reservaDeSalas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> lista = service.listarUsuario();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody @Valid Usuario usuario){
        service.criarUsuarios(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Criado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid Usuario atualizacaoUsuario){
        service.atualizarUsuario(id, atualizacaoUsuario);
        return ResponseEntity.ok("Usuario atualizado !!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        service.deletarUsuario(id);
        return ResponseEntity.ok("Usuario cancelado !!");
    }
}
