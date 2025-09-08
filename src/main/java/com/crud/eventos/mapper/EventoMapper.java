package com.crud.eventos.mapper;

import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.dto.LocalDto;
import com.crud.eventos.dto.ParticipanteDto;
import com.crud.eventos.model.Evento;
import com.crud.eventos.model.EventoParticipante;
import com.crud.eventos.model.Participante;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventoMapper {

    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    Evento requestToEntity(EventoRequestDto eventoRequestDto);
    default List<ParticipanteDto> participantesToParticipantesDto(List<Participante> participantes, List<Boolean> presencas_confirmadas){
        List<ParticipanteDto> participanteDtos = new ArrayList<>();

        participantes.forEach(participante -> {
            presencas_confirmadas.forEach(presenca ->{

                ParticipanteDto participanteDto = new ParticipanteDto(participante.getNome(),participante.getEmail(),presenca);
                participanteDtos.add(participanteDto);
            });

        });
        return participanteDtos;
    }
    EventoResponseDto entityToResponse(Evento evento, List<ParticipanteDto> participantes);
    default List<EventoResponseDto> listaEntityToListaResponse(List<Evento> listaDeEventos, List<ParticipanteDto> participantes){
        List<EventoResponseDto> listaEventoResponseDto = new ArrayList<>();
        listaDeEventos.forEach(evento -> {
                LocalDto localDto = new LocalDto(evento.getLocal().getNome(), evento.getLocal().getEndereco(), evento.getLocal().getCapacidade());
                EventoResponseDto eventoResponseDto = new EventoResponseDto(evento.getNome(), evento.getDescricao(), evento.getData(), localDto, participantes);

                listaEventoResponseDto.add(eventoResponseDto);
        });
        return listaEventoResponseDto;
    }
    void atualizarEvento(@MappingTarget Evento evento,EventoRequestDto eventoRequestDto);

}
