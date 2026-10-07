package com.example.reservaDeSalas.service;

import com.example.reservaDeSalas.exception.NaoEncontradoException;
import com.example.reservaDeSalas.exception.ValidacaoException;
import com.example.reservaDeSalas.model.Sala;
import com.example.reservaDeSalas.repository.SalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Transactional
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    @Transactional
    public Sala buscarPorId(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Sala não encontrada!!"));
    }

    @Transactional
    public void criarSala(Sala sala) {
        Boolean verificaSala = salaRepository.existsByNome(sala.getNome());
        if (verificaSala){
            throw new ValidacaoException("Sala ja criada");
        }
        salaRepository.save(sala);
    }

    @Transactional
    public void atualizarSala(Long id, Sala sala) {
        Sala salaAtualizada = buscarPorId(id);

        salaAtualizada.setNome(sala.getNome());
        salaAtualizada.setCapacidade(sala.getCapacidade());
        salaAtualizada.setAtiva(sala.isAtiva());

        salaRepository.save(salaAtualizada);
    }

    @Transactional
    public void desativarSala(Long id) {
        Sala sala = buscarPorId(id);
        sala.setAtiva(false);

        salaRepository.save(sala);
    }

    @Transactional
    public void deletarSala(Long id) {
        Sala sala = buscarPorId(id);
        salaRepository.delete(sala);
    }


}
