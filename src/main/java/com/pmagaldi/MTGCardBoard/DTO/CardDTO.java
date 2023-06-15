package com.pmagaldi.MTGCardBoard.DTO;


import org.springframework.beans.BeanUtils;

import com.pmagaldi.MTGCardBoard.entities.Card;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {
    
    private Integer id;
    private String name;
    private String lang;
    private String manaCost;
    private Integer cmc;
    private String[] colorIdentity;
    private boolean foil;
    private String setName;
    private String imageUrl;
    
    public CardDTO() {

    }

    public CardDTO(Card entity){
        BeanUtils.copyProperties(entity, this);
    }
}
