package com.pmagaldi.MTGCardBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmagaldi.MTGCardBoard.DTO.DeckDTO;
import com.pmagaldi.MTGCardBoard.exceptions.BusinessException;
import com.pmagaldi.MTGCardBoard.service.DeckService;

@RestController
@RequestMapping(value = "/deck")
public class DeckController {
    
    @Autowired
    private DeckService deckService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<DeckDTO>> findAll(){
        try{
            List<DeckDTO> result = deckService.findAll();
            return ResponseEntity.ok().body(result);
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeckDTO> findById(@PathVariable Integer id){
        try{
            DeckDTO result = deckService.findById(id);
            return ResponseEntity.ok().body(result);
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> postDeck(@RequestBody DeckDTO deck){
        try{
            deckService.postDeck(deck);
            return ResponseEntity.ok().build();
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping
    public ResponseEntity<DeckDTO> updateDeck(@RequestBody DeckDTO deck){
        try{
            DeckDTO result = deckService.updateDeck(deck);
            return ResponseEntity.ok().body(result);
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDeck(@PathVariable Integer id){
        try{
            deckService.deleteDeck(id);
            return ResponseEntity.ok().build();
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
