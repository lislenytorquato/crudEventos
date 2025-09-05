package com.crud.eventos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ParticipanteDto {

    private String nome;
    private String email;
    private boolean presenca_confirmada;
}
