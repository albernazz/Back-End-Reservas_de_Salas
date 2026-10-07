package com.example.reservaDeSalas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fim;

    @NotNull
    private int quantidadeDePessoas;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public Reserva() {
    }

    public Reserva(Long id, Sala sala, Usuario usuario, LocalDateTime inicio, LocalDateTime fim, int quantidadeDePessoas, Status status) {
        this.id = id;
        this.sala = sala;
        this.usuario = usuario;
        this.inicio = inicio;
        this.fim = fim;
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.status = status != null ? status : Status.ATIVA;
    }

}
