package com.example.wryp;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NewPlan {

    String data;
    String tag;
    String description;

    ArrayList<NewPlan> arrayOfPlans = new ArrayList<>();

    public NewPlan(String data, String tag, String description){
        this.data = data;
        this.tag = tag;
        this.description = description;
    }


    @NonNull
    @Override
    public String toString() {
        return data + tag + description;
    }
}
