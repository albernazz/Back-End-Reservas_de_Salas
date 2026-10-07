package com.example.reservaDeSalas.exception;

import com.example.reservaDeSalas.dto.ErroRespostaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    // 1. Trata 404 (NaoEncontradoException)
    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroRespostaDTO> tratar404(NaoEncontradoException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 2. Trata 400 (Sua regra de negócio - ValidacaoException)
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErroRespostaDTO> tratar400RegraDeNegocio(ValidacaoException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(ex.getMessage(), null);
        return ResponseEntity.badRequest().body(erro);
    }

    // 3. Trata 400 (Validações de formulário do @Valid: @NotNull, @NotBlank, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> tratar400BeanValidation(MethodArgumentNotValidException ex) {
        List<ErroRespostaDTO.DadosErroCampo> errosDeCampo = ex.getFieldErrors()
                .stream()
                .map(erro -> new ErroRespostaDTO.DadosErroCampo(erro.getField(), erro.getDefaultMessage()))
                .toList();

        ErroRespostaDTO erro = new ErroRespostaDTO("Dados inválidos na requisição.", errosDeCampo);
        return ResponseEntity.badRequest().body(erro);
    }
}