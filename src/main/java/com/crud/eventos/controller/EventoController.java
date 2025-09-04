package com.crud.eventos.controller;

import com.crud.eventos.dto.EventoRequestDto;
import com.crud.eventos.dto.EventoResponsetDto;
import com.crud.eventos.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/eventos")
public class EventoController {

    public final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoResponsetDto> criar(@RequestBody EventoRequestDto eventoRequestDto){
        EventoResponsetDto eventoResponsetDto = eventoService.criarEvento(eventoRequestDto);
        return new ResponseEntity<>(eventoResponsetDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponsetDto>> listar(@RequestBody EventoRequestDto eventoRequestDto){
        List<EventoResponsetDto> listaEventos = eventoService.listarEventos();
        return new ResponseEntity<>(listaEventos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponsetDto> atualizar(@PathVariable Long id, @RequestBody EventoRequestDto eventoRequestDto){
        EventoResponsetDto eventoResponsetDto = eventoService.atualizarEvento(id, eventoRequestDto);
        return new ResponseEntity<>(eventoResponsetDto, HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        eventoService.deletarEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
