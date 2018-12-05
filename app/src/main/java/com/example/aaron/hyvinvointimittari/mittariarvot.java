package com.example.aaron.hyvinvointimittari;

public class mittariarvot {
    private static final mittariarvot ourInstance = new mittariarvot();

    public static mittariarvot getInstance() {
        return ourInstance;
    }
    private int fysVointi;
    private float henVointi;
    private int weeklyFysVointi;
    private float weeklyHenVointi;
    private int alltimeFysVointi;
    private float alltimeHenVointi;
    private mittariarvot() {

    }
    public void setFysVointi(int fysVointi){
        this.fysVointi = fysVointi;
    }
    public void setWeeklyFysVointi(int fysVointi){
        this.weeklyFysVointi = fysVointi;
    }
    public void setAlltimeFysVointi(int fysVointi){
        this.alltimeFysVointi = fysVointi;
    }
    public void setHenVointi(int henVointi){
        this.henVointi = henVointi;
    }
    public void setWeeklyHenVointi(int henVointi){
        this.weeklyHenVointi= henVointi;
    }
    public void setAlltimeHenVointi(int henVointi){
        this.alltimeHenVointi = henVointi;
    }
}
