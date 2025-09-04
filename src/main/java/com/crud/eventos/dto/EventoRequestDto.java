package com.crud.eventos.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class EventoRequestDto {

    private String nome;
    private String descricao;
    private LocalDate data;
    private LocalDto local;
    private List<Long> idsParticipantes;
}
