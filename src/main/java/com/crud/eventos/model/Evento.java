package com.crud.eventos.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate data;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_id")
    private Local local;
    @OneToMany(mappedBy = "participante", cascade = CascadeType.ALL)
    private List<EventoParticipante> eventosParticipantes = new ArrayList<>();

}
