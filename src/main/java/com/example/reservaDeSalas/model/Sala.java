package com.example.reservaDeSalas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "salas")
@Getter
@Setter
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private int capacidade;

    @NotNull
    private boolean ativa;

    //pode ter varias reservas em uma sala
    @JsonIgnore
    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas;


}
