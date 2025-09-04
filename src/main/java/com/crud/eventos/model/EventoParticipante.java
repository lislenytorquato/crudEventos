package com.crud.eventos.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
public class EventoParticipante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Long id;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Participante participante;
    private boolean presenca_confirmada;

}
