package com.splitwise.demo.exceptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String message){
        super(message);
    }
}
