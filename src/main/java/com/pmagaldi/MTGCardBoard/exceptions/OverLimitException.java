package com.pmagaldi.MTGCardBoard.exceptions;

public class OverLimitException extends BusinessException{
    public OverLimitException(){
        super("Over the limit of quantity of cards in a deck");
    }

    public OverLimitException(String message){
        super(message);
    }
}
