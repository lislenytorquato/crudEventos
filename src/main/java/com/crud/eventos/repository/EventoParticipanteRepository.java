package com.crud.eventos.repository;

import com.crud.eventos.model.EventoParticipante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoParticipanteRepository extends JpaRepository<EventoParticipante,Long> {
}
