package com.splitwise.demo.commands;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {
    Map<String,Command> map;

    private static CommandRegistry INSTANCE;
    private CommandRegistry(){
        this.map = new HashMap<>();
    }
    public static CommandRegistry getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new CommandRegistry();
        }
        return INSTANCE;
    }

    public void register(String commandName,Command command){
        map.put(commandName,command);
    }
    public Optional<Command> get(String input){
        if(StringUtils.isEmpty(input)){
            return Optional.empty();
        }
        String[] split = input.split(" ");
        for (String s : split) {
            if(map.containsKey(s)){
                return Optional.of(map.get(s));
            }
        }
        return Optional.empty();
    }
}
