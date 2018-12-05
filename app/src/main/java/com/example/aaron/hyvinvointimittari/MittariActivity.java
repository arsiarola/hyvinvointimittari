package com.example.aaron.hyvinvointimittari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MittariActivity extends AppCompatActivity {
    private Button paiva;
    private Button viikko;
    private Button alusta;
    private float weeklyHenVointi;
    private int weeklyFysVointi;
    private float alltimeHenVointi;
    private int alltimeFysVointi;
    private float henVointi;
    private int fysVointi;
    private ProgressBar hen;
    private ProgressBar fys;
    private TextView fyysinen;
    private TextView henkinen;
    private float uusiHenk;
    private float viikkoUusiHenk;
    private float alustaUusiHenk;
    private int numberOfDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mittari);
        fys = findViewById(R.id.progressBarFys);
        hen = findViewById(R.id.progressBarHenk);
        Intent intent = getIntent();
        henVointi = intent.getFloatExtra("extra", 50f);
        fysVointi = intent.getIntExtra("fysVointi", 0);
        weeklyHenVointi = intent.getFloatExtra("weeklyHenVointi",50f);
        weeklyFysVointi = intent.getIntExtra("weeklyFysVointi",0);
        alltimeHenVointi = intent.getFloatExtra("alltimeHenVointi",50f);
        alltimeFysVointi = intent.getIntExtra("alltimeFysVointi",50);
        numberOfDays = intent.getIntExtra("päiviä",1);
        fyysinen = findViewById(R.id.fysLuku);
        fyysinen.setText(Integer.toString(fysVointi));
        henkinen = findViewById(R.id.henkLuku);
        uusiHenk = henVointi - 50;
        henkinen.setText(Integer.toString(Math.round(uusiHenk)));
        viikkoUusiHenk = weeklyHenVointi -50;
        alustaUusiHenk = alltimeHenVointi -50;
        hen.setProgress(Math.round(henVointi));
        fys.setProgress(fysVointi);

        final TextView fyysinenDailyProgress = findViewById(R.id.fyysinenDailyProgress);
        fyysinenDailyProgress.setText(Integer.toString(fysVointi) + "/100");

        //buttonit

        ////paiva napin teko ja onclicklistener
        paiva = findViewById(R.id.päivä);
        paiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hen.setProgress(Math.round(henVointi));
                fys.setProgress(fysVointi);
                fyysinen.setText(Integer.toString(fysVointi));
                henkinen.setText(Integer.toString(Math.round(uusiHenk)));
                fyysinenDailyProgress.setText(Integer.toString(fysVointi) + "/100");

            }
        });


        ////paiva pysyy valittuna kun painaa sitä

        paiva.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                paiva.setPressed(true);
                viikko.setPressed(false);
                alusta.setPressed(false);
                return true;
            }
        });

        ////viikko napin teko ja onclicklistener

        viikko = findViewById(R.id.viikko);
        viikko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hen.setProgress(Math.round(weeklyHenVointi/7));
                fys.setProgress(weeklyFysVointi/7);
                fyysinen.setText(Integer.toString(weeklyFysVointi));
                henkinen.setText(Integer.toString(Math.round(viikkoUusiHenk)));
                fyysinenDailyProgress.setText(Integer.toString(weeklyFysVointi) + "/700");

            }
        });

        ////viikko pysyy valittuna kun painaa sitä

        viikko.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                paiva.setPressed(false);
                viikko.setPressed(true);
                alusta.setPressed(false);
                return true;
            }
        });

        ////alusta napin teko ja onclicklistener
        alusta = findViewById(R.id.alustalähtien);
        alusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hen.setProgress(Math.round(alltimeHenVointi/numberOfDays));
                fys.setProgress(alltimeFysVointi/numberOfDays);
                fyysinen.setText(Integer.toString(alltimeFysVointi));
                henkinen.setText(Integer.toString(Math.round(alustaUusiHenk)));
                fyysinenDailyProgress.setText(Integer.toString(alltimeFysVointi) + "/∞");

            }
        });

        ////alusta pysyy valittuna kun painaa sitä

        alusta.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viikko.setPressed(false);
                paiva.setPressed(false);
                alusta.setPressed(true);
                return true;
            }
        });

    }
}
