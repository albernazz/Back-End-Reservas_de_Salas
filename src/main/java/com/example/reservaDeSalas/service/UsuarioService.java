package com.example.reservaDeSalas.service;

import com.example.reservaDeSalas.exception.NaoEncontradoException;
import com.example.reservaDeSalas.exception.ValidacaoException;
import com.example.reservaDeSalas.model.Usuario;
import com.example.reservaDeSalas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuario não encontrado!!"));
    }

    @Transactional
    public void criarUsuarios(@Valid Usuario usuario) {
       boolean verificaEmail = usuarioRepository.existsByEmail(usuario.getEmail());
       if (verificaEmail){
           throw new ValidacaoException("Este email ja pertence a outro usuario");
       }
       usuarioRepository.save(usuario);
    }

    @Transactional
    public void atualizarUsuario(Long id, @Valid Usuario atualizacaoUsuario) {
        Usuario usuario = buscarPorId(id);

        if (atualizacaoUsuario.getNome() != null && !atualizacaoUsuario.getNome().isBlank()) {
            usuario.setNome(atualizacaoUsuario.getNome());
        }
        if (atualizacaoUsuario.getEmail() != null && !atualizacaoUsuario.getEmail().isBlank()) {
            usuario.setEmail(atualizacaoUsuario.getEmail());
        }
        if (atualizacaoUsuario.getDepartamento() != null && !atualizacaoUsuario.getDepartamento().isBlank()) {
            usuario.setDepartamento(atualizacaoUsuario.getDepartamento());
        }

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
