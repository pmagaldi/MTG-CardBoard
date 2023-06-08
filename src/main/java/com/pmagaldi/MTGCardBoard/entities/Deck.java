package com.pmagaldi.MTGCardBoard.entities;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.pmagaldi.MTGCardBoard.exceptions.BusinessException;
import com.pmagaldi.MTGCardBoard.exceptions.OverFourCopiesException;
import com.pmagaldi.MTGCardBoard.exceptions.OverLimitException;
import com.pmagaldi.MTGCardBoard.exceptions.SingletonFormatException;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_deck")
public class Deck {
    
    private Integer id;
    private String name;
    private String[] colorsIdentity;
    private Format format;
    private List<Card> cards;

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColorsIdentity(String[] colorsIdentity) {
        this.colorsIdentity = colorsIdentity;
    }
    public void setFormat(Format format) {
        this.format = format;
    }

    public void setCards(Card card) throws BusinessException{

        if(this.format.equals(Format.COMMANDER) && cards.contains(card)){
            throw new SingletonFormatException();
        }

        if(this.format.equals(Format.COMMANDER) && cards.size() >= 100){
            throw new OverLimitException();
        }

        if(cards.contains(card)){
            AtomicInteger copies = new AtomicInteger();
            cards.forEach(x -> {
                if(copies.get() >= 4) {
                    throw new OverFourCopiesException();
                }
                if(x.equals(card)){
                    copies.getAndIncrement();
                }
            });
        }

        this.cards.add(card);
    }

}
