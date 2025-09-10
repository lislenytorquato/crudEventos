package com.crud.eventos.service;

import com.crud.eventos.dto.AtualizarEventoRequestDto;
import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.dto.ParticipanteDto;
import com.crud.eventos.exceptions.EventoException;
import com.crud.eventos.exceptions.EventoParticipanteException;
import com.crud.eventos.exceptions.LocalException;
import com.crud.eventos.exceptions.ParticipanteException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.crud.eventos.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    EventoResponseDto eventoResponse = TestHelper.responseDto(NOME_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO,NOME_LOCAL,ENDERECO_LOCAL,CAPACIDADE_LOCAL,NOME_1,EMAIL_1,PRESENCA_2,NOME_2,EMAIL_2,PRESENCA_2);

    Evento evento = mapper.requestToEntity(eventoRequest);
    Mockito.when(localRepository.save(Mockito.any(Local.class))).thenReturn(evento.getLocal());
    Mockito.when(participanteRepository.findById(1L)).thenReturn(Optional.ofNullable(participante1()));
    Mockito.when(participanteRepository.findById(2L)).thenReturn(Optional.ofNullable(participante2()));
    Mockito.when(eventoParticipanteRepository.save(Mockito.any(EventoParticipante.class))).thenReturn(participante1().getEventosParticipantes().get(0));

    List<ParticipanteDto> participantes = mapper.participantesToParticipantesDto(List.of(participante1(), participante2()), List.of(PRESENCA_1, PRESENCA_2));
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
    Assertions.assertEquals(eventoResponse.getParticipantes().get(0).getNome(),responseDto.getParticipantes().get(0).getNome());
    Assertions.assertEquals(eventoResponse.getParticipantes().get(0).getEmail(),responseDto.getParticipantes().get(0).getEmail());
    Assertions.assertEquals(eventoResponse.getParticipantes().get(0).isPresenca_confirmada(),responseDto.getParticipantes().get(0).isPresenca_confirmada());
    Assertions.assertEquals(eventoResponse.getParticipantes().get(1).getNome(),responseDto.getParticipantes().get(1).getNome());
    Assertions.assertEquals(eventoResponse.getParticipantes().get(1).getEmail(),responseDto.getParticipantes().get(1).getEmail());
    Assertions.assertEquals(eventoResponse.getParticipantes().get(1).isPresenca_confirmada(),responseDto.getParticipantes().get(1).isPresenca_confirmada());



}
    @DisplayName("2- Deve listar evento")
    @Test
    void deveListarEvento(){
        List<Evento> eventos = new ArrayList<>();
        List<Participante> participantes = new ArrayList<>();
        List<Boolean> presencasConfirmadas = new ArrayList<>();

        eventos.add(evento());
        participantes.add(participante1());
        participantes.add(participante2());
        presencasConfirmadas.add(participante1().getEventosParticipantes().get(0).isPresenca_confirmada());
        presencasConfirmadas.add(participante2().getEventosParticipantes().get(0).isPresenca_confirmada());
        Mockito.when(eventoRepository.findAll()).thenReturn(eventos);

        List<ParticipanteDto> participantesDtos = mapper.participantesToParticipantesDto(participantes, presencasConfirmadas);
        List<EventoResponseDto> eventoResponse = mapper.listaEntityToListaResponse(eventos,participantesDtos);

        List<EventoResponseDto> response = this.eventoService.listarEventos();


        Assertions.assertEquals(eventoResponse.size(),response.size());
        Assertions.assertEquals(eventoResponse.get(0).getNome(),response.get(0).getNome());
        Assertions.assertEquals(eventoResponse.get(0).getDescricao(),response.get(0).getDescricao());
        Assertions.assertEquals(eventoResponse.get(0).getData(),response.get(0).getData());
        Assertions.assertEquals(eventoResponse.get(0).getLocal().getNome(),response.get(0).getLocal().getNome());
        Assertions.assertEquals(eventoResponse.get(0).getLocal().getEndereco(),response.get(0).getLocal().getEndereco());
        Assertions.assertEquals(eventoResponse.get(0).getLocal().getCapacidade(),response.get(0).getLocal().getCapacidade());
        Assertions.assertEquals(eventoResponse.get(0).getParticipantes().get(0).getNome(), response.get(0).getParticipantes().get(0).getNome());
        Assertions.assertEquals(eventoResponse.get(0).getParticipantes().get(0).getEmail(), response.get(0).getParticipantes().get(0).getEmail());
        Assertions.assertEquals(eventoResponse.get(0).getParticipantes().get(0).isPresenca_confirmada(), response.get(0).getParticipantes().get(0).isPresenca_confirmada());
        Assertions.assertEquals(eventoResponse.get(0).getParticipantes().get(0).getNome(), response.get(0).getParticipantes().get(0).getNome());
        Assertions.assertEquals(eventoResponse.get(0).getParticipantes().get(0).getEmail(), response.get(0).getParticipantes().get(0).getEmail());
        Assertions.assertEquals(eventoResponse.get(0).getParticipantes().get(0).isPresenca_confirmada(), response.get(0).getParticipantes().get(0).isPresenca_confirmada());

    }
    @DisplayName("3- Deve deletar evento")
    @Test
    void deveDeletarEvento(){
        Optional<Evento> evento = Optional.of(evento());
        Optional<EventoParticipante> eventoParticipante1 = Optional.of(eventoParticipante1());
        Optional<EventoParticipante> eventoParticipante2 = Optional.of(eventoParticipante2());
        Optional<Local> local = Optional.of(local());

        Mockito.when(eventoRepository.findById(1L)).thenReturn(evento);
        Mockito.when(eventoParticipanteRepository.findById(1L)).thenReturn(eventoParticipante1);
        Mockito.when(eventoParticipanteRepository.findById(2L)).thenReturn(eventoParticipante2);

        Mockito.doNothing().when(eventoParticipanteRepository).delete(eventoParticipante1.get());
        Mockito.doNothing().when(eventoParticipanteRepository).delete(eventoParticipante2.get());
        Mockito.doNothing().when(eventoRepository).delete(evento.get());

        Mockito.when(localRepository.findById(1L)).thenReturn(local);
        Mockito.doNothing().when(localRepository).delete(local.get());

        this.eventoService.deletarEvento(1L);

        Mockito.verify(eventoRepository,atMost(1)).findById(1L);
        Mockito.verify(eventoParticipanteRepository,atMost(1)).findById(1L);
        Mockito.verify(eventoParticipanteRepository,atMost(1)).findById(1L);
        Mockito.verify(eventoParticipanteRepository,atMost(1)).delete(eventoParticipante1.get());
        Mockito.verify(eventoParticipanteRepository,atMost(1)).delete(eventoParticipante2.get());
        Mockito.verify(eventoRepository,atMost(1)).delete(evento.get());
        Mockito.verify(localRepository,atMost(1)).findById(1L);
        Mockito.verify(localRepository,atMost(1)).delete(local.get());
    }

    @DisplayName("4- Deve atualizar evento")
    @Test
    void deveAtualizarEvento(){
        AtualizarEventoRequestDto atualizarEventoRequestDto = atualizarEventoRequestDto();
        EventoResponseDto eventoResponse = TestHelper.responseDto(atualizarEventoRequestDto.getNome(),atualizarEventoRequestDto.getDescricao(),atualizarEventoRequestDto.getData(),atualizarEventoRequestDto.getLocal().getNome(),atualizarEventoRequestDto.getLocal().getEndereco(),atualizarEventoRequestDto.getLocal().getCapacidade(),NOME_1,EMAIL_1,PRESENCA_2,NOME_2,EMAIL_2,PRESENCA_2);
        List<Boolean> presencasConfirmadas = List.of(PRESENCA_1,PRESENCA_2);
        List<Participante> participantes = List.of(participante1(),participante2());

        Optional<Evento> evento = Optional.of(evento());

        Mockito.when(eventoRepository.findById(ID_EVENTO)).thenReturn(evento);
        mapper.atualizarEvento(evento.get(),atualizarEventoRequestDto);
        Mockito.when(eventoRepository.save(evento.get())).thenReturn(evento());
        Mockito.when(participanteRepository.findAll()).thenReturn(participantes);
        List<ParticipanteDto> participanteDtos = mapper.participantesToParticipantesDto(participantes, presencasConfirmadas);
        mapper.entityToResponse(evento(), participanteDtos);

        EventoResponseDto response = this.eventoService.atualizarEvento(ID_EVENTO, atualizarEventoRequestDto);

        Assertions.assertEquals(eventoResponse.getNome(), response.getNome());
        Assertions.assertEquals(eventoResponse.getDescricao(), response.getDescricao());
        Assertions.assertEquals(eventoResponse.getData(),response.getData());
        Assertions.assertEquals(eventoResponse.getLocal().getNome(), response.getLocal().getNome());
        Assertions.assertEquals(eventoResponse.getLocal().getEndereco(), response.getLocal().getEndereco());
        Assertions.assertEquals(eventoResponse.getLocal().getCapacidade(), response.getLocal().getCapacidade());
        Assertions.assertEquals(2,response.getParticipantes().size());
        Assertions.assertEquals(eventoResponse.getParticipantes().get(0).getNome(),response.getParticipantes().get(0).getNome());
        Assertions.assertEquals(eventoResponse.getParticipantes().get(0).getEmail(),response.getParticipantes().get(0).getEmail());
        Assertions.assertEquals(eventoResponse.getParticipantes().get(0).isPresenca_confirmada(),response.getParticipantes().get(0).isPresenca_confirmada());
        Assertions.assertEquals(eventoResponse.getParticipantes().get(1).getNome(),response.getParticipantes().get(1).getNome());
        Assertions.assertEquals(eventoResponse.getParticipantes().get(1).getEmail(),response.getParticipantes().get(1).getEmail());
        Assertions.assertEquals(eventoResponse.getParticipantes().get(1).isPresenca_confirmada(),response.getParticipantes().get(1).isPresenca_confirmada());


    }

    @DisplayName("5- Deve lançar exceção quando evento nao encontrado")
    @Test
    void deveLancarExcecaoQuandoEventoNaoEncontrado(){
        Assertions.assertThrows(EventoException.class,()->this.eventoService.atualizarEvento(1L,atualizarEventoRequestDto()), MENSAGEM_EVENTO_EXCEPTION);
        Assertions.assertThrows(EventoException.class,()->this.eventoService.deletarEvento(1L), MENSAGEM_EVENTO_EXCEPTION);
    }

    @DisplayName("6- Deve lançar exceção quando local nao encontrado")
    @Test
    void deveLancarExcecaoQuandoLocalNaoEncontrado(){
    Mockito.when(eventoRepository.findById(ID_EVENTO)).thenReturn(Optional.of(evento()));
    Mockito.when(eventoParticipanteRepository.findById(ID_EVENTO_PARTICIPANTE_1)).thenReturn(Optional.of(eventoParticipante1()));
        Mockito.when(eventoParticipanteRepository.findById(ID_EVENTO_PARTICIPANTE_2)).thenReturn(Optional.of(eventoParticipante2()));
        Assertions.assertThrows(LocalException.class,()->this.eventoService.deletarEvento(ID_EVENTO), MENSAGEM_LOCAL_EXCEPTION);
    }

    @DisplayName("7- Deve lançar exceção quando eventoparticipante nao encontrado")
    @Test
    void deveLancarExcecaoQuandoEventoParticipanteNaoEncontrado(){
        Mockito.when(eventoRepository.findById(ID_EVENTO)).thenReturn(Optional.of(evento()));
      Assertions.assertThrows(EventoParticipanteException.class,()->this.eventoService.deletarEvento(1L), MENSAGEM_EVENTO_PARTICIPANTE_EXCEPTION);
    }
    @DisplayName("8- Deve lançar exceção quando participante nao encontrado")
    @Test
    void deveLancarExcecaoQuandoParticipanteNaoEncontrado(){
        EventoRequestDto eventoRequest = TestHelper.requestDto(NOME_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO,NOME_LOCAL,ENDERECO_LOCAL,CAPACIDADE_LOCAL,ID_PARTICIPANTE_1,ID_PARTICIPANTE_2);
        Evento evento = mapper.requestToEntity(eventoRequest);
        Mockito.when(localRepository.save(Mockito.any(Local.class))).thenReturn(evento.getLocal());
        Assertions.assertThrows(ParticipanteException.class,()->this.eventoService.criarEvento(eventoRequest), MENSAGEM_PARTICIPANTE_EXCEPTION);
    }
}