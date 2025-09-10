package com.crud.eventos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Builder
@Getter
@AllArgsConstructor
public class AtualizarEventoRequestDto {
    private String nome;
    private String descricao;
    private LocalDate data;
    private LocalDto local;
}
