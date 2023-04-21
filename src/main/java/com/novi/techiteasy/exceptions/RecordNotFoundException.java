package com.novi.techiteasy.exceptions;

public class RecordNotFoundException extends RuntimeException //Alles wat gebeurt tijdens het runnen - fouten je nog wel kan opvangen door Exception te schrijven
{
    public RecordNotFoundException(){
        super();
    }

    public RecordNotFoundException(String message){
        super(message);
    }

}
