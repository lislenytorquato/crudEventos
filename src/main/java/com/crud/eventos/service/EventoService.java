package com.crud.eventos.service;

import com.crud.eventos.dto.AtualizarEventoRequestDto;
import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.dto.ParticipanteDto;
import com.crud.eventos.mapper.EventoMapper;
import com.crud.eventos.model.Evento;
import com.crud.eventos.model.EventoParticipante;
import com.crud.eventos.model.Local;
import com.crud.eventos.model.Participante;
import com.crud.eventos.repository.EventoParticipanteRepository;
import com.crud.eventos.repository.EventoRepository;
import com.crud.eventos.repository.LocalRepository;
import com.crud.eventos.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    EventoMapper mapper = EventoMapper.INSTANCE;
    private final EventoRepository eventoRepository;
    private final LocalRepository localRepository;
    private final ParticipanteRepository participanteRepository;
    private final EventoParticipanteRepository eventoParticipanteRepository;

    public EventoService(EventoRepository eventoRepository, LocalRepository localRepository, ParticipanteRepository participanteRepository, EventoParticipanteRepository eventoParticipanteRepository){
        this.eventoRepository = eventoRepository;
        this.localRepository = localRepository;
        this.participanteRepository = participanteRepository;
        this.eventoParticipanteRepository = eventoParticipanteRepository;
    }

    public EventoResponseDto criarEvento(EventoRequestDto eventoRequestDto){
        Evento evento = mapper.requestToEntity(eventoRequestDto);

        Local local = evento.getLocal();
        localRepository.save(local);
        List<ParticipanteDto> participanteDtos = participantesDto(eventoRequestDto);

        eventoRepository.save(evento);

       return mapper.entityToResponse(evento,participanteDtos);
    }

    public List<EventoResponseDto> listarEventos(){
        List<Participante> participantes = new ArrayList<>();
        List<Boolean> presencasConfirmadas = new ArrayList<>();

        List<Evento> listaDeEventos = eventoRepository.findAll();

        listaDeEventos.forEach(evento -> {
            evento.getEventosParticipantes().forEach(eventoParticipante -> {
                participantes.add(eventoParticipante.getParticipante());
                presencasConfirmadas.add(eventoParticipante.isPresenca_confirmada());
            });
        });
        List<ParticipanteDto> participantesDtos = mapper.participantesToParticipantesDto(participantes, presencasConfirmadas);
        return mapper.listaEntityToListaResponse(listaDeEventos,participantesDtos);
    }

    public EventoResponseDto atualizarEvento(Long id, AtualizarEventoRequestDto atualizarEventoRequestDto){
        List<Boolean> presencasConfirmadas = new ArrayList<>();

        Evento evento = eventoRepository.findById(id).orElseThrow();

        mapper.atualizarEvento(evento,atualizarEventoRequestDto);

        eventoRepository.save(evento);

        List<Participante> participantes = participanteRepository.findAll();
        participantes.forEach(participante -> {
            participante.getEventosParticipantes().forEach(eventoParticipante -> {
                presencasConfirmadas.add(eventoParticipante.isPresenca_confirmada());
            });
        });

        List<ParticipanteDto> participanteDtos = mapper.participantesToParticipantesDto(participantes, presencasConfirmadas);

        return mapper.entityToResponse(evento,participanteDtos);
    }

    public void deletarEvento(Long id){
        Evento evento = eventoRepository.findById(id).orElseThrow();

        evento.getEventosParticipantes().forEach(eventoParticipante -> {

                EventoParticipante eventoParticipanteEncontrado = eventoParticipanteRepository.findById(eventoParticipante.getId()).orElseThrow();
                eventoParticipanteRepository.delete(eventoParticipanteEncontrado);

        });
        eventoRepository.delete(evento);

        Local local = localRepository.findById(evento.getLocal().getId()).orElseThrow();
        localRepository.delete(local);
    }

    private List<ParticipanteDto> participantesDto (EventoRequestDto eventoRequestDto){
        List<Participante> participantes = new ArrayList<>();
        List<Boolean> presencas_confirmadas = new ArrayList<>();

        eventoRequestDto.getIdsParticipantes().forEach(idParticipante ->{
            Participante participante = participanteRepository.findById(idParticipante).orElseThrow();
            participantes.add(participante);
        });
        participantes.forEach(participante -> {
            participante.getEventosParticipantes().forEach(eventoParticipante -> {
                presencas_confirmadas.add(eventoParticipante.isPresenca_confirmada());
                eventoParticipanteRepository.save(eventoParticipante);
            });
        });


        return mapper.participantesToParticipantesDto(participantes,presencas_confirmadas);
    }
}
