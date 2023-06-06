package com.pmagaldi.MTGCardBoard.exceptions;

public class BusinessRunTimeException extends RuntimeException{
    public BusinessRunTimeException(){
        super();
    }

    public BusinessRunTimeException(String message){
        super(message);
    }
}
