package com.felipesantos.projetosecurity.controller;

import com.felipesantos.projetosecurity.dto.CardDTO;
import com.felipesantos.projetosecurity.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardDTO>> findAll(){
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findById(@PathVariable Long id){
        CardDTO dto = cardService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CardDTO> create(@RequestBody CardDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
                .toUri();
        CardDTO response = cardService.create(dto);
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> update(@PathVariable Long id, @RequestBody CardDTO dto){
        CardDTO response = cardService.update(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardDTO> delete(@PathVariable Long id){
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
