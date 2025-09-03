package com.crud.eventos.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
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
