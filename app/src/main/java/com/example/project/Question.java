package com.example.project;

import androidx.annotation.NonNull;

public class Question {
    private final String name;
    private final String category;
    private final Number size;
    private final String type;
    private final String ID;

    public Question(String inName, String inLocation, Number inSize, String inType, String inID){
        name = inName;
        category = inLocation;
        size = inSize;
        type = inType;
        ID = inID;
    }

    public String info(){
        return name + " är " + category + "." ;
    }

    @NonNull
    @Override
    public String toString(){
        return "Viken färg har " + name + "?";
    }

    public String moreInfo(){
        return "Namn = " + name + " Färg = " + category + " Storlek = " + size + " tillagd av = " + type + " ID = " + ID;
    }
}
