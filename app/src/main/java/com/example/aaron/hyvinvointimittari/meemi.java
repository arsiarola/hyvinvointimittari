package com.example.aaron.hyvinvointimittari;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * Näytetään mmemejä josta voi valita piristi tai masensi, jolloin henkinen hyvinvointi reagoi siihen
 * uuden meemin saa painamalla uusi meemi tai poistumalla acitivitysta ja tulemalla takaisin
 */
public class meemi extends AppCompatActivity {
    private Random rand = new Random();
    private int random;
    private ImageView meemi;
    private float henVointi;
    private int fysVointi;
    private int  weeklyFysVointi;
    private float weeklyHenVointi;
    private float alltimeHenVointi;
    private int alltimeFysVointi;
    private int vanhaWeeklyFysVointi;
    private float vanhaWeeklyHenVointi;
    private int vanhaAlltimeFysVointi;
    private float vanhaAllTimeHenVointi;

    private final int[]  images = {R.drawable.yks, R.drawable.kaks, R.drawable.kol,R.drawable.nel,R.drawable.viis,R.drawable.kuus,
            R.drawable.seiska,R.drawable.kasi,R.drawable.ysi,R.drawable.kymppi,R.drawable.ykstoist,R.drawable.kakstoist,R.drawable.koltoist,
            R.drawable.neltoist,R.drawable.viistoist,R.drawable.kuustoist,R.drawable.seitsemantoist,R.drawable.kaheksantoist,
            R.drawable.yheksantoist,R.drawable.kakskyt,R.drawable.kaksyks,R.drawable.kakskaks,R.drawable.kakskol,R.drawable.kaksnel,
            R.drawable.kaksvii,R.drawable.kakskuu,R.drawable.kakssei,R.drawable.kakskas,R.drawable.kaksysi,R.drawable.kolkyt,
            R.drawable.kolyks,R.drawable.kolkaks,R.drawable.kolkol,R.drawable.kolnel,R.drawable.kolvii,R.drawable.kolkuu,R.drawable.kolsei,
            R.drawable.kolkasi,R.drawable.kolysi,R.drawable.nelkyt,R.drawable.nelyks,R.drawable.nelkaks,R.drawable.nelkol,R.drawable.nelnel,
            R.drawable.nelvii,R.drawable.nelkuu,R.drawable.nelsei,R.drawable.nelkasi,R.drawable.nelysi,R.drawable.viiskyt};

    /**
     * Alustetaan buttonit ja muuttujat, listätään niihin logiikka.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meemi);
        Intent intent = getIntent();
        henVointi = intent.getFloatExtra("extra", 50f);
        fysVointi = intent.getIntExtra("fysVointi", 0);
        weeklyHenVointi = intent.getFloatExtra("weeklyHenVointi",50f);
        weeklyFysVointi = intent.getIntExtra("weeklyFysVointi",0);
        alltimeHenVointi = intent.getFloatExtra("alltimeHenVointi",50f);
        alltimeFysVointi = intent.getIntExtra("alltimeFysVointi",0);
        vanhaAlltimeFysVointi = intent.getIntExtra("vanhaAllFys", 0);
        vanhaAllTimeHenVointi = intent.getFloatExtra("vanhaAllHen",50f);
        vanhaWeeklyFysVointi = intent.getIntExtra("vanhaWeekFys", 0);
        vanhaWeeklyHenVointi = intent.getFloatExtra("vanhaWeekHen", 50f);

        Button b1=findViewById(R.id.b1);
        int x = R.drawable.ic_launcher_background;
        meemi=findViewById(R.id.imageView);

        //ensimmäinen nappi lisää hyvinvointia
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(henVointi<100){
                    weeklyHenVointi+= -henVointi+henVointi*(float)1.1;
                    alltimeHenVointi+= -henVointi+henVointi*(float)1.1;
                    henVointi*= (float)1.1;
                }
                if (henVointi>=100){
                    henVointi = 100;
                    weeklyHenVointi = vanhaWeeklyHenVointi + 100;
                    alltimeHenVointi = vanhaAllTimeHenVointi + 100;
                }
            }
        });
        //toinen nappi arpoo uuden kuvan
        Button b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meemi.setImageResource(images[rand.nextInt(images.length)]);
            }
        });
        //kolmas nappi vähentää henkistä hyvinvointia
        Button b3=findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(henVointi>0){
                    weeklyHenVointi-= henVointi-henVointi*(float)0.9;
                    alltimeHenVointi-= henVointi-henVointi*(float)0.9;
                    henVointi*= (float)0.9;
                }
                if (Math.round(henVointi) == 0) {
                    henVointi = 1;
                }else if(henVointi >=100){
                    henVointi=100;
                    alltimeHenVointi = vanhaAllTimeHenVointi+50;
                }
                if (Math.round(weeklyHenVointi) == 0) {
                    weeklyHenVointi = vanhaAllTimeHenVointi;
                }
                if (Math.round(alltimeHenVointi) == 0) {
                    alltimeHenVointi = vanhaAllTimeHenVointi;
                }

            }
        });
        //arvotaan uusi kuva kun activityn avaa
        meemi.setImageResource(images[rand.nextInt(images.length)]);
        }

    /**
     * kun poistutaan acitivysta tallentaan datan muutokset
     */
    public void onPause() {
        //tallentaa tietoa
        SharedPreferences prefPut = getSharedPreferences(MainActivity.PREF, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putFloat("henVointi", henVointi);
        prefEditor.putInt("fysVointi", fysVointi);
        prefEditor.putInt(MainActivity.weeklyFys, weeklyFysVointi);
        prefEditor.putFloat(MainActivity.weeklyHen, weeklyHenVointi);
        prefEditor.putInt(MainActivity.alltimeFys, alltimeFysVointi);
        prefEditor.putFloat(MainActivity.alltimeHen, alltimeHenVointi);
        prefEditor.commit();
        super.onPause();
    }
}
