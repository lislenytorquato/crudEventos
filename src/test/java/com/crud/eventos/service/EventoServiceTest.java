package com.crud.eventos.service;

import com.crud.eventos.repository.EventoParticipanteRepository;
import com.crud.eventos.repository.EventoRepository;
import com.crud.eventos.repository.LocalRepository;
import com.crud.eventos.repository.ParticipanteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

    @Mock
    EventoRepository eventoRepository;

    @Mock
    LocalRepository localRepository;

    @Mock
    ParticipanteRepository participanteRepository;

    @Mock
    EventoParticipanteRepository eventoParticipanteRepository;

    @DisplayName("1- Deve criar evento")
    @Test
    public void deveCriarEvento(){

    }
    @DisplayName("2- Deve listar eventos")
    @Test
    public void deveListarEvento(){

    }
    @DisplayName("3- Deve atualizar eventos")
    @Test
    public void deveAtualizarEvento(){

    }
    @DisplayName("4- Deve deletar evento")
    @Test
    public void deveDeletarEvento(){

    }

}
