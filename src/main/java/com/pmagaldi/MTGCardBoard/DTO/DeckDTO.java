package com.pmagaldi.MTGCardBoard.DTO;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pmagaldi.MTGCardBoard.entities.Card;
import com.pmagaldi.MTGCardBoard.entities.Deck;
import com.pmagaldi.MTGCardBoard.entities.Format;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeckDTO {
    
    private Integer id;
    private String name;
    private String[] colorsIdentity;
    private Format format;
    private List<Card> cards;

    public DeckDTO(){

    }

    public DeckDTO(Deck entity){
        BeanUtils.copyProperties(entity, this);
    }
}
