package com.example.aaron.hyvinvointimittari;

/**
 * luodaan olotilat luokka
 */
public class olotilat {
    private double multiplier;
    private String olotila;

    /**
     *
     * @param olotila on henkinen olotila
     * @param multiplier tähän olotilaan liittyvä kerroin
     */
    public olotilat(String olotila, double multiplier){
        this.multiplier = multiplier;
        this.olotila = olotila;

    }
    /**
     *
     * @return palauttaa olotilan kertoimen
     */
    public double getMultiplier(){
        return this.multiplier;
    }
    /**
     *
     * @return palauttaa olotilan nimen
     */
    public String getOlotila(){
        return this.olotila;
    }
}
