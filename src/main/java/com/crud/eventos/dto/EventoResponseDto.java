package com.crud.eventos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class EventoResponseDto {

    private String nome;
    private String descricao;
    private LocalDate data;
    private LocalDto local;
}
