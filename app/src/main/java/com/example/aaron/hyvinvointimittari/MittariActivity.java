package com.example.aaron.hyvinvointimittari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Tällä activitylla näytetään henkinen ja fyysinen hyvinvointi
 *
 */
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
    private ImageView hymy;

    /**
     * Alustetaan buttonit ja muuttujat, listätään niihin logiikka.
     * @param savedInstanceState
     */
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
        viikkoUusiHenk = numberOfDays%7*(-50)+weeklyHenVointi;
        alustaUusiHenk = alltimeHenVointi -50*numberOfDays;
        hen.setProgress(Math.round(henVointi));
        fys.setProgress(fysVointi);
        hymy = findViewById(R.id.hymy);
        final TextView fyysinenDailyProgress = findViewById(R.id.fyysinenDailyProgress);
        fyysinenDailyProgress.setText(Integer.toString(fysVointi) + "/100");
        if(henVointi<50){
            hymy.setImageResource(R.drawable.suru);
        }else if (henVointi >=50){
            hymy.setImageResource(R.drawable.hymy);
        }
        //buttonit

        ////paiva napin teko ja onclicklistener
        paiva = findViewById(R.id.päivä);
        paiva.setPressed(true);
        ////paiva pysyy valittuna kun painaa sitä

        paiva.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(henVointi<50){
                    hymy.setImageResource(R.drawable.suru);
                }else if (henVointi >=50){
                    hymy.setImageResource(R.drawable.hymy);
                }
                paiva.setPressed(true);
                viikko.setPressed(false);
                alusta.setPressed(false);
                hen.setProgress(Math.round(henVointi));
                fys.setProgress(fysVointi);
                fyysinen.setText(Integer.toString(fysVointi));
                henkinen.setText(Integer.toString(Math.round(uusiHenk)));
                fyysinenDailyProgress.setText(Integer.toString(fysVointi) + "/100");
                return true;
            }
        });

        ////viikko napin teko ja onclicklistener

        viikko = findViewById(R.id.viikko);

        ////viikko pysyy valittuna kun painaa sitä

        viikko.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(weeklyHenVointi<50){
                    hymy.setImageResource(R.drawable.suru);
                }else if (weeklyHenVointi >=50){
                    hymy.setImageResource(R.drawable.hymy);
                }
                paiva.setPressed(false);
                viikko.setPressed(true);
                alusta.setPressed(false);
                int paivienMaara;
                hen.setProgress(Math.round(weeklyHenVointi));
                fys.setProgress(weeklyFysVointi/7);
                fyysinen.setText(Integer.toString(weeklyFysVointi));
                henkinen.setText(Integer.toString(Math.round(viikkoUusiHenk)));
                fyysinenDailyProgress.setText(Integer.toString(weeklyFysVointi) + "/700");
                return true;
            }
        });

        ////alusta napin teko ja onclicklistener
        alusta = findViewById(R.id.alustalähtien);

        ////alusta pysyy valittuna kun painaa sitä

        alusta.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(alltimeHenVointi/numberOfDays<50){
                    hymy.setImageResource(R.drawable.suru);
                }else if (alltimeHenVointi/numberOfDays>=50){
                    hymy.setImageResource(R.drawable.hymy);
                }
                viikko.setPressed(false);
                paiva.setPressed(false);
                alusta.setPressed(true);
                hen.setProgress(Math.round(alltimeHenVointi/numberOfDays));
                fys.setProgress(alltimeFysVointi/numberOfDays);
                fyysinen.setText(Integer.toString(alltimeFysVointi));
                henkinen.setText(Integer.toString(Math.round(alustaUusiHenk/numberOfDays)));
                fyysinenDailyProgress.setText(Integer.toString(alltimeFysVointi) + "/∞");
                return true;
            }
        });

    }
}
