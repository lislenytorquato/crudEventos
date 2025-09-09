package com.crud.eventos.controller;

import com.crud.eventos.dto.AtualizarEventoRequestDto;
import com.crud.eventos.helper.TestHelper;
import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.service.EventoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.crud.eventos.helper.TestHelper.*;

@ExtendWith(MockitoExtension.class)
public class EventoControllerTest {

    @InjectMocks
    EventoController eventoController;

    @Mock
    EventoService eventoService;

    public static EventoRequestDto eventoRequest;
    public static EventoResponseDto eventoResponse;

    @BeforeEach
    void setup(){
            eventoRequest = TestHelper.requestDto(NOME_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO,NOME_LOCAL,ENDERECO_LOCAL,CAPACIDADE_LOCAL,ID_PARTICIPANTE_1,ID_PARTICIPANTE_2);
            eventoResponse = TestHelper.responseDto(NOME_EVENTO,DESCRICAO_EVENTO,DATA_EVENTO,NOME_LOCAL,ENDERECO_LOCAL,CAPACIDADE_LOCAL,NOME_1,EMAIL_1,PRESENCA_1,NOME_2,EMAIL_2,PRESENCA_2);
    }

    @DisplayName("1- Deve criar evento")
    @Test
    public void deveCriarEvento(){

        Mockito.when(eventoService.criarEvento(eventoRequest)).thenReturn(eventoResponse);
        ResponseEntity<EventoResponseDto> response = this.eventoController.criar(eventoRequest);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }
    @DisplayName("2- Deve listar eventos")
    @Test
    public void deveListarEvento(){

        Mockito.when(eventoService.listarEventos()).thenReturn(List.of(eventoResponse));
        ResponseEntity<List<EventoResponseDto>> response = this.eventoController.listar(eventoRequest);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @DisplayName("3- Deve atualizar eventos")
    @Test
    public void deveAtualizarEvento(){
        AtualizarEventoRequestDto atualizarEventoRequestDto = atualizarEventoRequestDto();

        Mockito.when(eventoService.atualizarEvento(1L,atualizarEventoRequestDto)).thenReturn(eventoResponse);
        ResponseEntity<EventoResponseDto> response = this.eventoController.atualizar(1L, atualizarEventoRequestDto);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }
    @DisplayName("4- Deve deletar evento")
    @Test
    public void deveDeletarEvento(){

        Mockito.doNothing().when(eventoService).deletarEvento(1L);
        ResponseEntity<Void> response = this.eventoController.deletar(1L);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

    }

}
