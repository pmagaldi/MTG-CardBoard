package com.pmagaldi.MTGCardBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
