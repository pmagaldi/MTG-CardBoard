package com.pmagaldi.MTGCardBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmagaldi.MTGCardBoard.DTO.CardDTO;
import com.pmagaldi.MTGCardBoard.entities.Card;
import com.pmagaldi.MTGCardBoard.exceptions.BusinessException;
import com.pmagaldi.MTGCardBoard.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Transactional(readOnly = true)
    public List<CardDTO> findAll() throws BusinessException{

        List<Card> cards = cardRepository.findAll();

        if (cards.isEmpty()) {
            throw new BusinessException("Don't have any card in your collection");
        }

        return cards.stream().map(CardDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CardDTO findById(Integer id) throws BusinessException{

        Optional<Card> card = cardRepository.findById(id);

        if(!card.isPresent()){
            throw new BusinessException("Not found this card");
        }
        
        return new CardDTO(card.get());
    }

    @Transactional(readOnly = true)
    public CardDTO findByName(String name) throws BusinessException{
        
        Card card = cardRepository.findByName(name);

        if(!Optional.ofNullable(card).isPresent()){
            throw new BusinessException("Not found this card");
        }
        
        return new CardDTO(card);
    }
    

    public void postCard(CardDTO cardDTO) throws BusinessException{
        try{
            cardRepository.save(
                Card.builder()
                    .name(cardDTO.getName())
                    .lang(cardDTO.getLang())
                    .manaCost(cardDTO.getManaCost())
                    .cmc(cardDTO.getCmc())
                    .colorIdentity(cardDTO.getColorIdentity())
                    .foil(cardDTO.isFoil())
                    .setName(cardDTO.getSetName())
                    .imageUrl(cardDTO.getImageUrl())
                .build()
            );
        } catch (RuntimeException exception){
            throw new BusinessException("Not able to save card");
        }
    }

    public void deleteCard(Integer id) throws BusinessException{
        Optional<Card> card = cardRepository.findById(id);

        if(!card.isPresent()){
            throw new BusinessException("Unable to find card by id: "+id);
        }

        try{
            cardRepository.delete(card.get());
        } catch (RuntimeException exception) {
            throw new BusinessException("Unable to delete card by id: "+id);
        }
    }
}
