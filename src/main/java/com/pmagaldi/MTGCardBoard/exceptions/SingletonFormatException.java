package com.pmagaldi.MTGCardBoard.exceptions;

public class SingletonFormatException extends BusinessException{

    public SingletonFormatException(){
        super("Singleton format deck, card not valid to insert");
    }

    public SingletonFormatException(String message){
        super(message);
    }
    
}
