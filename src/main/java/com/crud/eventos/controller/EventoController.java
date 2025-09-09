package com.crud.eventos.controller;

import com.crud.eventos.dto.AtualizarEventoRequestDto;
import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponseDto;
import com.crud.eventos.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoResponseDto> criar(@RequestBody EventoRequestDto eventoRequestDto){
        EventoResponseDto eventoResponseDto = eventoService.criarEvento(eventoRequestDto);
        return new ResponseEntity<>(eventoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDto>> listar(@RequestBody EventoRequestDto eventoRequestDto){
        List<EventoResponseDto> listaEventos = eventoService.listarEventos();
        return new ResponseEntity<>(listaEventos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDto> atualizar(@PathVariable Long id, @RequestBody AtualizarEventoRequestDto eventoRequestDto){
        EventoResponseDto eventoResponseDto = eventoService.atualizarEvento(id, eventoRequestDto);
        return new ResponseEntity<>(eventoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        eventoService.deletarEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
