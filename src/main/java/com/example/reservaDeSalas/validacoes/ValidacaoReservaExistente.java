package com.example.reservaDeSalas.validacoes;

import com.example.reservaDeSalas.exception.ValidacaoException;
import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoReservaExistente {

    @Autowired
    private ReservaRepository reservaRepository;

    public void validar(Reserva reserva){
        boolean verificaReserva = reservaRepository.existsById(reserva.getId());
        if (verificaReserva){
            throw new ValidacaoException("Ja criada ja criada");
        }
    }
}
