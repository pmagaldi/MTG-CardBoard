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

    @Transactional(readOnly = true)
    public DeckDTO findById(Integer id) throws BusinessException{

        Optional<Deck> deck = deckRepository.findById(id);

        if (!deck.isPresent()){
            throw new BusinessException("Don't have any card in your collection");
        }

        return new DeckDTO(deck.get());
    }

    public void postDeck(DeckDTO deckDTO) throws BusinessException{
        try{
            deckRepository.save(
                new Deck(
                    deckDTO.getId(),
                    deckDTO.getName(),
                    deckDTO.getColorsIdentity(),
                    deckDTO.getFormat(),
                    deckDTO.getCards()
                )
            );
        } catch (RuntimeException exception){
            throw new BusinessException("not able to save deck");
        }
    }

    public DeckDTO updateDeck(DeckDTO deckDTO) throws BusinessException{
        this.deleteDeck(deckDTO.getId());
        this.postDeck(deckDTO);
        return deckDTO;
    }

    public void deleteDeck(Integer id) throws BusinessException{
        Optional<Deck> deck = deckRepository.findById(id);
        
        if(!deck.isPresent()){
            throw new BusinessException("Unable to find deck by id: "+id);
        }

        try{
            deckRepository.delete(deck.get());
        } catch (RuntimeException exception){
            throw new BusinessException("Unable to delete deck by id: "+id);
        }
    }
}
