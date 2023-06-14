package com.pmagaldi.MTGCardBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/{name}")
    public ResponseEntity<CardDTO> findById(@PathVariable String name){
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

    @DeleteMapping
    public ResponseEntity<Void> deleteCard(@RequestBody Integer id){
        try{
            cardService.deleteCard(id);
            return ResponseEntity.ok().build();
        } catch (BusinessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
