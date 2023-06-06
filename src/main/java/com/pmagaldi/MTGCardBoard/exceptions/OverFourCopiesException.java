package com.pmagaldi.MTGCardBoard.exceptions;

public class OverFourCopiesException extends BusinessRunTimeException{
    
    public OverFourCopiesException(){
        super("Over four copies of this card in this Deck, invalid card to insert");
    }

    public OverFourCopiesException(String message){
        super(message);
    }

}
