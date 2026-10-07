package com.example.reservaDeSalas.service;

import com.example.reservaDeSalas.exception.NaoEncontradoException;
import com.example.reservaDeSalas.exception.ValidacaoException;
import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.model.Status;
import com.example.reservaDeSalas.repository.ReservaRepository;
import com.example.reservaDeSalas.validacoes.ValidacoesDeReservas;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private List<ValidacoesDeReservas> validacoesDeReservas;

    @Transactional
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Transactional
    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Reserva não encontrada!!"));
    }

    @Transactional
    public void criarReserva(Reserva reserva){
        validacoesDeReservas.forEach(v -> v.validar(reserva));
        reservaRepository.save(reserva);
    }

    @Transactional
    public void atualizarReserva(Long id, @Valid Reserva atualizacaoReserva) {
        Reserva reserva = buscarPorId(id);

        if (atualizacaoReserva.getSala() != null) {
            reserva.setSala(atualizacaoReserva.getSala());
        }
        if (atualizacaoReserva.getUsuario() != null) {
            reserva.setUsuario(atualizacaoReserva.getUsuario());
        }
        if (atualizacaoReserva.getInicio() != null) {
            reserva.setInicio(atualizacaoReserva.getInicio());
        }
        if (atualizacaoReserva.getFim() != null) {
            reserva.setFim(atualizacaoReserva.getFim());
        }
        if (atualizacaoReserva.getQuantidadeDePessoas() > 0) {
            reserva.setQuantidadeDePessoas(atualizacaoReserva.getQuantidadeDePessoas());
        }
        if (atualizacaoReserva.getStatus() != null) {
            reserva.setStatus(atualizacaoReserva.getStatus());
        }

        validacoesDeReservas.forEach(v -> v.validar(reserva));

        reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = buscarPorId(id);
        if (reserva.getStatus() == Status.CANCELADA) {
            throw new ValidacaoException("A reserva já se encontra cancelada.");
        }
        reserva.setStatus(Status.CANCELADA);
    }

    @Transactional
    public void deletarReserva(Long id) {
        Reserva reserva = buscarPorId(id);
        reservaRepository.delete(reserva);
    }
}
