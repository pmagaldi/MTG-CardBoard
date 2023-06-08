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
    public Card findById(Integer id) throws BusinessException{

        Optional<Card> card = cardRepository.findById(id);

        if(!card.isPresent()){
            throw new BusinessException("Not found this card");
        }
        
        return card.get();
    }

    @Transactional(readOnly = true)
    public Card findByName(String name) throws BusinessException{
        
        Card card = cardRepository.findByName(name);

        if(!Optional.ofNullable(card).isPresent()){
            throw new BusinessException("Not found this card");
        }
        
        return card;
    }
    

    public void postCard(CardDTO cardDTO) throws BusinessException{
        try{
            cardRepository.save(
                Card.builder()
                    .id(cardDTO.getId())
                    .name(cardDTO.getName())
                    .lang(cardDTO.getLang())
                    .manaCost(cardDTO.getManaCost())
                    .cmc(cardDTO.getCmc())
                    .colorIdentity(cardDTO.getColorIdentity())
                    .legalities(cardDTO.getLegalities())
                    .foil(cardDTO.isFoil())
                    .setName(cardDTO.getSetName())
                    .imageUrl(cardDTO.getImageUrl())
                .build()
            );
        } catch (RuntimeException e){
            throw new BusinessException("Not able to save card");
        }
    }
}
