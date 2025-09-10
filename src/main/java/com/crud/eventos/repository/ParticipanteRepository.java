package com.crud.eventos.repository;

import com.crud.eventos.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
}
