package com.example.just_drive;

public class State {
    private String name;

    public State( String name){

        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public  void setName(String name) {
        this.name=name;
    }
}
