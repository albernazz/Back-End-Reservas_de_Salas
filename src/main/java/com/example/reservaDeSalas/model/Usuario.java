package com.example.reservaDeSalas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    private String departamento;

    //um usuario pode ter varias reservas
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;
}
