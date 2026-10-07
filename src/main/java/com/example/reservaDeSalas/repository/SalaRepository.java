package com.example.reservaDeSalas.repository;

import com.example.reservaDeSalas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Boolean existsByNome(String nome);
}
