package com.crud.eventos.service;

import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.dto.ParticipanteDto;
import com.crud.eventos.helper.TestHelper;
import com.crud.eventos.mapper.EventoMapper;
import com.crud.eventos.model.Evento;
import com.crud.eventos.model.EventoParticipante;
import com.crud.eventos.model.Local;
import com.crud.eventos.model.Participante;
import com.crud.eventos.repository.EventoParticipanteRepository;
import com.crud.eventos.repository.EventoRepository;
import com.crud.eventos.repository.LocalRepository;
import com.crud.eventos.repository.ParticipanteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.crud.eventos.helper.TestHelper.*;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

@InjectMocks
EventoService eventoService;

@Mock
EventoRepository eventoRepository;

@Mock
LocalRepository localRepository;

@Mock
ParticipanteRepository participanteRepository;

@Mock
EventoParticipanteRepository eventoParticipanteRepository;


EventoMapper mapper = EventoMapper.INSTANCE;


@BeforeEach
void setup(){
}

@DisplayName("1- Deve criar evento")
@Test
void deveCriarEvento(){

    EventoRequestDto eventoRequest = TestHelper.requestDto(NOME_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO,NOME_LOCAL,ENDERECO_LOCAL,CAPACIDADE_LOCAL,ID_PARTICIPANTE_1,ID_PARTICIPANTE_2);
    EventoResponseDto eventoResponse = TestHelper.responseDto(NOME_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO,NOME_LOCAL,ENDERECO_LOCAL,CAPACIDADE_LOCAL,NOME_1,EMAIL_1,PRESENCA_1,NOME_2,EMAIL_2,PRESENCA_2);

    Evento evento = mapper.requestToEntity(eventoRequest);
    Mockito.when(localRepository.save(Mockito.any(Local.class))).thenReturn(evento.getLocal());
    Mockito.when(participanteRepository.findById(1L)).thenReturn(Optional.ofNullable(participante1(ID_PARTICIPANTE_1, NOME_1, EMAIL_1)));
    Mockito.when(participanteRepository.findById(2L)).thenReturn(Optional.ofNullable(participante2(ID_PARTICIPANTE_2, NOME_2, EMAIL_2)));
    Mockito.when(eventoParticipanteRepository.save(Mockito.any(EventoParticipante.class))).thenReturn(participante1(ID_PARTICIPANTE_1, NOME_1, EMAIL_1).getEventosParticipantes().get(0));

    List<ParticipanteDto> participantes = mapper.participantesToParticipantesDto(List.of(participante1(ID_PARTICIPANTE_1, NOME_1, EMAIL_1), participante1(ID_PARTICIPANTE_2, NOME_2, EMAIL_2)), List.of(PRESENCA_1, PRESENCA_2));
    Mockito.when(eventoRepository.save(Mockito.any(Evento.class))).thenReturn(evento);
    mapper.entityToResponse(evento,participantes);
    EventoResponseDto responseDto = this.eventoService.criarEvento(eventoRequest);

    Assertions.assertEquals(NOME_EVENTO, responseDto.getNome());
    Assertions.assertEquals(DESCRICAO_EVENTO, responseDto.getDescricao());
    Assertions.assertEquals(DATA_EVENTO,responseDto.getData());
    Assertions.assertEquals(NOME_LOCAL,responseDto.getLocal().getNome());
    Assertions.assertEquals(ENDERECO_LOCAL,responseDto.getLocal().getEndereco());
    Assertions.assertEquals(CAPACIDADE_LOCAL,responseDto.getLocal().getCapacidade());
    Assertions.assertEquals(2,responseDto.getParticipantes().size());
    Assertions.assertEquals(NOME_1,responseDto.getParticipantes().get(0).getNome());
    Assertions.assertEquals(EMAIL_1,responseDto.getParticipantes().get(0).getEmail());
    Assertions.assertEquals(PRESENCA_1,responseDto.getParticipantes().get(0).isPresenca_confirmada());
    Assertions.assertEquals(NOME_2,responseDto.getParticipantes().get(1).getNome());
    Assertions.assertEquals(EMAIL_2,responseDto.getParticipantes().get(1).getEmail());
    Assertions.assertEquals(PRESENCA_2,responseDto.getParticipantes().get(1).isPresenca_confirmada());



}
}