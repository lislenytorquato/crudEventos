package com.crud.eventos.service;

import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponsetDto;
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

import java.util.List;
import java.util.Optional;

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

    public EventoResponsetDto criarEvento(EventoRequestDto eventoRequestDto){
        Evento evento = mapper.requestToEntity(eventoRequestDto);

        Local local = evento.getLocal();
        localRepository.save(local);

        eventoRequestDto.getEventosParticipantes().forEach(eventoParticipante -> {
            if (eventoParticipante.isPresenca_confirmada()){
                participanteRepository.save(eventoParticipante.getParticipante());
                eventoParticipanteRepository.save(eventoParticipante);
            }
        });

        eventoRepository.save(evento);

        return mapper.entityToResponse(evento);
    }
    public List<EventoResponsetDto> listarEventos(){
        List<Evento> listaDeEventos = eventoRepository.findAll();
        return mapper.listaEntityToListaResponse(listaDeEventos);
    }
    public EventoResponsetDto atualizarEvento(Long id,EventoRequestDto eventoRequestDto){
        Evento evento = eventoRepository.findById(id).orElseThrow();
        mapper.atualizarEvento(evento,eventoRequestDto);
        eventoRepository.save(evento);
        return mapper.entityToResponse(evento);
    }

    public void deletarEvento(Long id){
        Evento evento = eventoRepository.findById(id).orElseThrow();
        eventoRepository.deleteById(id);
    }
}
