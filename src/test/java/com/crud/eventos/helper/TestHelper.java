package com.crud.eventos.helper;

import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.dto.LocalDto;
import com.crud.eventos.dto.ParticipanteDto;
import com.crud.eventos.model.Evento;
import com.crud.eventos.model.EventoParticipante;
import com.crud.eventos.model.Local;
import com.crud.eventos.model.Participante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestHelper {
    public static final Long ID_PARTICIPANTE_1 = 1L;
    public static final Long ID_PARTICIPANTE_2 = 2L;
    public static final String NOME_1 = "Ana Souza";
    public static final String EMAIL_1 = "ana.souza@example.com";
    public static final boolean PRESENCA_1 = true;
    public static final String NOME_2 = "Carlos Lima";
    public static final String EMAIL_2 = "carlos.lima@example.com";
    public static final boolean PRESENCA_2 = false;
    public static final String NOME_EVENTO = "Workshop de Testes Automatizados";
    public static final String DESCRICAO_EVENTO = "Evento sobre boas práticas de QA";
    public static final LocalDate DATA_EVENTO = LocalDate.of(2025, 9, 20);
    public static final String NOME_LOCAL = "Auditório Central";
    public static final String ENDERECO_LOCAL = "Rua das Flores, 123";
    public static final int CAPACIDADE_LOCAL = 100;

    public static EventoRequestDto requestDto(String nomeEvento, String descricaoEvento, LocalDate dataEvento, String nomeLocal, String enderecoLocal, int capacidadeLocal, Long idParticipante1, Long idParticipante2){

        return EventoRequestDto.builder()
                    .nome(nomeEvento)
                    .descricao(descricaoEvento)
                    .data(dataEvento)
                    .local(LocalDto.builder()
                            .nome(nomeLocal)
                            .endereco(enderecoLocal)
                            .capacidade(capacidadeLocal)
                            .build())
                    .idsParticipantes(List.of(idParticipante1,idParticipante2))
                    .build();
    }

    public static EventoResponseDto responseDto(String nomeEvento, String descricaoEvento,
                                                LocalDate dataEvento, String nomeLocal, String enderecoLocal,
                                                int capacidadeLocal, String nomeParticipante1,String emailParticipante1,boolean presencaParticipante1,
                                                String nomeParticipante2,String emailParticipante2,boolean presencaParticipante2){
        List<ParticipanteDto> listaParticipantes = new ArrayList<>();
        listaParticipantes.add(participanteDto(nomeParticipante1,emailParticipante1,presencaParticipante1));
        listaParticipantes.add(participanteDto(nomeParticipante1,emailParticipante1,presencaParticipante1));

        return EventoResponseDto.builder()
                .nome(nomeEvento)
                .descricao(descricaoEvento)
                .data(dataEvento)
                .local(LocalDto.builder()
                        .nome(nomeLocal)
                        .endereco(enderecoLocal)
                        .capacidade(capacidadeLocal)
                        .build())
                .participantes(listaParticipantes)
                .build();
    }
    public static ParticipanteDto participanteDto(String nome, String email, boolean presenca){
        return ParticipanteDto.builder()
                .nome(nome)
                .email(email)
                .presenca_confirmada(presenca)
                .build();
    }
    public static Participante participante1(Long id, String nome, String email){
        List<EventoParticipante> eventoParticipantes = new ArrayList<>();
        eventoParticipantes.add(eventoParticipante1());
        eventoParticipantes.add(eventoParticipante2());

        return Participante.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .eventosParticipantes(eventoParticipantes.stream().toList()).build();
    }
    public static Participante participante2(Long id, String nome, String email){
        List<EventoParticipante> eventoParticipantes = new ArrayList<>();
        eventoParticipantes.add(eventoParticipante2());

        return Participante.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .eventosParticipantes(eventoParticipantes.stream().toList()).build();
    }
    public static Participante participante(Long id, String nome, String email){

        return Participante.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .build();
    }
    public static EventoParticipante eventoParticipante1(){
        return EventoParticipante.builder().evento(
                Evento.builder()
                        .id(1L)
                        .nome(NOME_EVENTO)
                        .data(DATA_EVENTO)
                        .descricao(DESCRICAO_EVENTO)
                                .build()).participante(
                                        Participante.builder()
                                                .id(ID_PARTICIPANTE_1)
                                                .nome(NOME_1)
                                                .email(EMAIL_1)
                                                .build()
                )
                        .build();
    }
    public static EventoParticipante eventoParticipante2(){
        return EventoParticipante.builder().evento(
                        Evento.builder()
                                .id(1L)
                                .nome(NOME_EVENTO)
                                .data(DATA_EVENTO)
                                .descricao(DESCRICAO_EVENTO)
                                .build()).participante(
                        Participante.builder()
                                .id(ID_PARTICIPANTE_2)
                                .nome(NOME_2)
                                .email(EMAIL_2)
                                .build()
                )
                .build();
    }
    public static Local local(){
        return Local.builder()
                .id(1L)
                .nome(NOME_LOCAL)
                .endereco(ENDERECO_LOCAL)
                .capacidade(CAPACIDADE_LOCAL)
                .build();
    }
    public static Evento evento(){
        return Evento.builder()
                .id(1L)
                .nome(NOME_EVENTO)
                .descricao(DESCRICAO_EVENTO)
                .local(local())
                .eventosParticipantes(List.of(eventoParticipante1(),eventoParticipante2()))
                .build();
    }
}
