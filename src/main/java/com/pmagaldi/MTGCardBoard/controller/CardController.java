package com.pmagaldi.MTGCardBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pmagaldi.MTGCardBoard.DTO.CardDTO;
import com.pmagaldi.MTGCardBoard.exceptions.BusinessException;
import com.pmagaldi.MTGCardBoard.service.CardService;

@RestController
@RequestMapping(value = "/card")
public class CardController {
    
    @Autowired
    private CardService cardService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CardDTO>> findAll(){
        try{
            List<CardDTO> result = cardService.findAll();
            return ResponseEntity.ok().body(result);
        } catch (BusinessException exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CardDTO> findById(@PathVariable Integer id){
        try{
            CardDTO result = cardService.findById(id);
            return ResponseEntity.ok().body(result);
        } catch (BusinessException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/find")
    public ResponseEntity<CardDTO> findById(@RequestParam String name){
        try{
            CardDTO result = cardService.findByName(name);
            return ResponseEntity.ok().body(result);
        } catch (BusinessException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> postCard(@RequestBody CardDTO card){
        try{
            cardService.postCard(card);
            return ResponseEntity.ok().build();
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer id){
        try{
            cardService.deleteCard(id);
            return ResponseEntity.ok().build();
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Integer id, @RequestBody CardDTO card) {
        try {
            var result = cardService.updateCard(id, card);
            return ResponseEntity.ok(result);
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
