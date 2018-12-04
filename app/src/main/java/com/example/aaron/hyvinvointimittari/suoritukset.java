package com.example.aaron.hyvinvointimittari;

public class suoritukset {
    private double timeMultiplier;
    private String olotila;
    public suoritukset(String suoritus, double multiplier){
        this.timeMultiplier = multiplier;
        this.olotila = suoritus;

    }
    public double getMultiplier(){
        return this.timeMultiplier;
    }
    public String getOlotila(){
        return this.olotila;
    }
}