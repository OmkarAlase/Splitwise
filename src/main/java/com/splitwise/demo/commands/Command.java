package com.splitwise.demo.commands;

import com.splitwise.demo.exceptions.InvalidCommandException;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Command {
    public void validateAndExecute(String command) throws InvalidCommandException{
        validate(command);
        execute(command);
    }
    public abstract void validate(String command) throws InvalidCommandException;
    public abstract void execute(String command);
}
