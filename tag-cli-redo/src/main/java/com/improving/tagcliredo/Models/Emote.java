package com.improving.tagcliredo.Models;

import org.springframework.stereotype.Component;

public class Emote {

    private String name;
    private String message;

    public Emote(String name, String message){
        this.name = name;
        this.message = message;
    }


    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
