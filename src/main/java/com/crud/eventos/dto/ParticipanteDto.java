package com.crud.eventos.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDto {

    private String nome;
    private String email;
    private boolean presenca_confirmada;
}
