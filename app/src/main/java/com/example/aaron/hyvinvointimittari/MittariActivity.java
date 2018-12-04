package com.example.aaron.hyvinvointimittari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MittariActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mittari);
        ProgressBar fys = findViewById(R.id.progressBarFys);
        ProgressBar hen = findViewById(R.id.progressBarHenk);
        Intent intent = getIntent();
        int henVointi = intent.getIntExtra("extra", 0);
        int fysVointi = intent.getIntExtra("fysVointi", 0);
        TextView fyysinen = findViewById(R.id.fysLuku);
        fyysinen.setText(Integer.toString(fysVointi));
        TextView henkinen = findViewById(R.id.henkLuku);
        henkinen.setText(Integer.toString(henVointi));
        hen.setProgress(henVointi);
        fys.setProgress(fysVointi);

        //buttonit

        Button paiva = findViewById(R.id.päivä);
        paiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button viikko = findViewById(R.id.viikko);
        viikko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button allTime = findViewById(R.id.alustalähtien);
        allTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

