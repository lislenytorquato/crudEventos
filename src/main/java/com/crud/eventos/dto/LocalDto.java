package com.crud.eventos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LocalDto {
    private String nome;
    private String endereco;
    private int capacidade;
}
