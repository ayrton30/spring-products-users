package com.coderhouse.exception;

public class IdNotFoundException extends Exception {
    private String msg;

    public IdNotFoundException(){
        super("Id no existente");
    }
}
