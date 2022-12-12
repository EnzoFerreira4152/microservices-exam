package com.dh.catalog.exceptions;

public class CircuitBreakerException extends Exception{

    public CircuitBreakerException(String msg){
        super(msg);
    }

}
