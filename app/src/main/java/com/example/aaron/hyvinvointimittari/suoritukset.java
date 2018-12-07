package com.example.aaron.hyvinvointimittari;

/**
 * luodaan suoritukset luokka
 */
public class suoritukset {
    private double timeMultiplier;
    private String suoritus;

    /**
     *
     * @param suoritus on fyysinen aktiviteeti
     * @param multiplier tähän fyysiseen aktiviteettiin liittyy kerroin
     */
    public suoritukset(String suoritus, double multiplier){
        this.timeMultiplier = multiplier;
        this.suoritus = suoritus;

    }

    /**
     * @return palauttaa fyysiseen aktiviteettin liittyvän kertoimen
     */
    public double getMultiplier(){
        return this.timeMultiplier;
    }

    /**
     *
     * @return palautta fyysisen suorituksen nimen
     */
    public String getSuoritus(){
        return this.suoritus;
    }
}