package com.example.reservaDeSalas.validacoes;

import com.example.reservaDeSalas.exception.ValidacaoException;
import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.model.Sala;
import com.example.reservaDeSalas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeDominio implements ValidacoesDeReservas {

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public void validar(Reserva reserva) {
        // 1. Busca a sala completa no banco de dados usando o ID recebido no JSON
        Sala salaCompleta = salaRepository.findById(reserva.getSala().getId())
                .orElseThrow(() -> new ValidacaoException("Sala não encontrada"));

        // 2. Opcional, mas recomendado: atualiza a reserva com a sala preenchida
        reserva.setSala(salaCompleta);

        // 3. Faz as validações utilizando o objeto salaCompleta que veio do banco
        if (!salaCompleta.isAtiva()) {
            throw new ValidacaoException("Reserva status: Inativa");
        }
        if (reserva.getInicio().isAfter(reserva.getFim())) {
            throw new ValidacaoException("Data de Inicio não pode ser maior que a data de fim");
        }
        if (reserva.getQuantidadeDePessoas() <= 0) {
            throw new ValidacaoException("Capacidade de pessoas deve ser maior que ZERO");
        }
        if (reserva.getInicio().isEqual(reserva.getFim())) {
            throw new ValidacaoException("Data de Inicio não pode ser igual que a data de fim");
        }
        if (reserva.getQuantidadeDePessoas() > salaCompleta.getCapacidade()) {
            throw new ValidacaoException("Quantidade de pessoas não pode ser maior que a capacidade da sala");
        }
    }
}