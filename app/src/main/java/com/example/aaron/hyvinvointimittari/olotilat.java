package com.example.aaron.hyvinvointimittari;

public class olotilat {
    private double multiplier;
    private String olotila;
    public olotilat(String olotila, double multiplier){
        this.multiplier = multiplier;
        this.olotila = olotila;

    }
    public double getMultiplier(){
        return this.multiplier;
    }
    public String getOlotila(){
        return this.olotila;
    }
}
