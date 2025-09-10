package com.crud.eventos.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoResponseDto {

    private String nome;
    private String descricao;
    private LocalDate data;
    private LocalDto local;
    private List<ParticipanteDto> participantes;
}
