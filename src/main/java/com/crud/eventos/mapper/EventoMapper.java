package com.crud.eventos.mapper;

import com.crud.eventos.dto.*;
import com.crud.eventos.model.Evento;
import com.crud.eventos.model.Participante;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventoMapper {

    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    Evento requestToEntity(EventoRequestDto eventoRequestDto);
    EventoResponseDto entityToResponse(Evento evento);
    List<EventoResponseDto> listaEntityToListaResponse(List<Evento> listaDeEventos);
    void atualizarEvento(@MappingTarget Evento evento,EventoRequestDto eventoRequestDto);
}
