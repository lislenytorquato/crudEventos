package com.crud.eventos.dto;

import com.crud.eventos.model.EventoParticipante;
import com.crud.eventos.model.Local;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class EventoResponsetDto {

    private String nome;
    private String descricao;
    private LocalDate data;
    private Local local;
    private List<EventoParticipante> eventosParticipantes;
}
