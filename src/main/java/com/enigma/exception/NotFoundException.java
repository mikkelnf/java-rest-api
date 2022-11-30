package com.enigma.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Data is not found");
    }
}
