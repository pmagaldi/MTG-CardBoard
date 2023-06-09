package com.pmagaldi.MTGCardBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmagaldi.MTGCardBoard.DTO.DeckDTO;
import com.pmagaldi.MTGCardBoard.entities.Deck;
import com.pmagaldi.MTGCardBoard.exceptions.BusinessException;
import com.pmagaldi.MTGCardBoard.repository.DeckRepository;

@Service
public class DeckService {
    
    @Autowired
    DeckRepository deckRepository;

    @Transactional(readOnly = true)
    public List<DeckDTO> findAll() throws BusinessException{

        List<Deck> decks = deckRepository.findAll();

        if (decks.isEmpty()){
            throw new BusinessException("Don't have any card in your collection");
        }

        return decks.stream().map(DeckDTO::new).toList();
    }
}
