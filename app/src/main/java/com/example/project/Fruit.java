package com.example.project;

import androidx.annotation.NonNull;

public class Fruit {
    private final String name;
    private final String category;

    public Fruit(String inName, String inLocation){
        name = inName;
        category = inLocation;
    }

    public String info(){
        return name + " is located in " + category;
    }

    @NonNull
    @Override
    public String toString(){
        return name;
    }
}
