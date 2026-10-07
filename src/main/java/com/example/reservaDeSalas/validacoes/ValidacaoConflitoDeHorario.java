package com.example.reservaDeSalas.validacoes;

import com.example.reservaDeSalas.exception.ValidacaoException;
import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.model.Status;
import com.example.reservaDeSalas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoConflitoDeHorario implements ValidacoesDeReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void validar(Reserva reserva) {
        if (reserva.getStatus() == Status.CANCELADA) {
            return;
        }

        boolean possuiConflito;

        // Se a reserva já tem ID (Atualização), ignoramos ela própria na busca
        if (reserva.getId() != null) {
            possuiConflito = reservaRepository.existsBySalaIdAndStatusNotAndIdNotAndInicioBeforeAndFimAfter(
                    reserva.getSala().getId(),
                    Status.CANCELADA,
                    reserva.getId(),
                    reserva.getFim(),
                    reserva.getInicio()
            );
        } else { // Se for criação (POST)
            possuiConflito = reservaRepository.existsBySalaIdAndStatusNotAndInicioBeforeAndFimAfter(
                    reserva.getSala().getId(),
                    Status.CANCELADA,
                    reserva.getFim(),
                    reserva.getInicio()
            );
        }

        if (possuiConflito) {
            throw new ValidacaoException("A sala selecionada já possui uma reserva ativa no horário solicitado.");
        }
    }
}