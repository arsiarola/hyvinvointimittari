package com.example.aaron.hyvinvointimittari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mittari);
        fys = findViewById(R.id.progressBarFys);
        hen = findViewById(R.id.progressBarHenk);
        Intent intent = getIntent();
        henVointi = intent.getFloatExtra("extra", 50);
        fysVointi = intent.getIntExtra("fysVointi", 0);
        weeklyHenVointi = intent.getFloatExtra("weeklyHenVointi",50);
        weeklyFysVointi = intent.getIntExtra("weeklyFysVointi",0);
        alltimeHenVointi = intent.getFloatExtra("alltimeHenVointi",50);
        alltimeFysVointi = intent.getIntExtra("alltimeFysVointi",50);
        fyysinen = findViewById(R.id.fysLuku);
        fyysinen.setText(Integer.toString(fysVointi));
        henkinen = findViewById(R.id.henkLuku);
        uusiHenk = henVointi - 50;
        henkinen.setText(Integer.toString(Math.round(uusiHenk)));
        viikkoUusiHenk = weeklyHenVointi -50;
        alustaUusiHenk = alltimeHenVointi -50;
        hen.setProgress(Math.round(henVointi));
        fys.setProgress(fysVointi);

        //buttonit

        paiva = findViewById(R.id.päivä);
        paiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hen.setProgress(Math.round(henVointi));
                fys.setProgress(fysVointi);
                fyysinen.setText(Integer.toString(fysVointi));
                henkinen.setText(Integer.toString(Math.round(uusiHenk)));
            }
        });

        viikko = findViewById(R.id.viikko);
        viikko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hen.setProgress(Math.round(weeklyHenVointi));
                fys.setProgress(weeklyFysVointi);
                fyysinen.setText(Integer.toString(weeklyFysVointi));
                henkinen.setText(Integer.toString(Math.round(viikkoUusiHenk)));
            }
        });

        alusta = findViewById(R.id.alustalähtien);
        alusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hen.setProgress(Math.round(alltimeHenVointi));
                fys.setProgress(alltimeFysVointi);
                fyysinen.setText(Integer.toString(alltimeFysVointi));
                henkinen.setText(Integer.toString(Math.round(alustaUusiHenk)));

            }
        });

    }
}
