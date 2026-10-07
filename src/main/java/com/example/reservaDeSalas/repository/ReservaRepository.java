package com.example.reservaDeSalas.repository;

import com.example.reservaDeSalas.model.Reserva;
import com.example.reservaDeSalas.model.Status;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository  extends JpaRepository<Reserva, Long> {

    boolean existsById(Long id);

    boolean existsBySalaIdAndStatusNotAndIdNotAndInicioBeforeAndFimAfter(Long id, Status status, Long id1, @NotNull LocalDateTime fim, @NotNull LocalDateTime inicio);

    boolean existsBySalaIdAndStatusNotAndInicioBeforeAndFimAfter(Long id, Status status, @NotNull LocalDateTime fim, @NotNull LocalDateTime inicio);
}
