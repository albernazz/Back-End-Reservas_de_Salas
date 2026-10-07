package com.example.reservaDeSalas.dto;

import java.util.List;

public record ErroRespostaDTO(
        String mensagem,
        List<DadosErroCampo> camposProblematicos
) {
    public record DadosErroCampo(String campo, String mensagem){}
}
