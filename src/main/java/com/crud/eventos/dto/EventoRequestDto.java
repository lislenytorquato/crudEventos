package com.crud.eventos.dto;

import com.crud.eventos.model.EventoParticipante;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EventoRequestDto {

    private String nome;
    private String descricao;
    private LocalDate data;
    private Local local;
    private List<EventoParticipante> eventosParticipantes = new ArrayList<>();
}
