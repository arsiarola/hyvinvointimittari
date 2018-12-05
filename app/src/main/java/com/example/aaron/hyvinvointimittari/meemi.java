package com.example.aaron.hyvinvointimittari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class meemi extends AppCompatActivity {
    private Random rand = new Random();
    private int random;
    private ImageView meemi;
    private final int[]  images = {R.drawable.yks, R.drawable.kaks, R.drawable.kol,R.drawable.nel,R.drawable.viis,R.drawable.kuus,
            R.drawable.seiska,R.drawable.kasi,R.drawable.ysi,R.drawable.kymppi,R.drawable.ykstoist,R.drawable.kakstoist,R.drawable.koltoist,
            R.drawable.neltoist,R.drawable.viistoist,R.drawable.kuustoist,R.drawable.seitsemantoist,R.drawable.kaheksantoist,
            R.drawable.yheksantoist,R.drawable.kakskyt,R.drawable.kaksyks,R.drawable.kakskaks,R.drawable.kakskol,R.drawable.kaksnel,
            R.drawable.kaksvii,R.drawable.kakskuu,R.drawable.kakssei,R.drawable.kakskas,R.drawable.kaksysi,R.drawable.kolkyt,
            R.drawable.kolyks,R.drawable.kolkaks,R.drawable.kolkol,R.drawable.kolnel,R.drawable.kolvii,R.drawable.kolkuu,R.drawable.kolsei,
            R.drawable.kolkasi,R.drawable.kolysi,R.drawable.nelkyt,R.drawable.nelyks,R.drawable.nelkaks,R.drawable.nelkol,R.drawable.nelnel,
            R.drawable.nelvii,R.drawable.nelkuu,R.drawable.nelsei,R.drawable.nelkasi,R.drawable.nelysi,R.drawable.viiskyt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meemi);
        Intent intent = getIntent();
        float henVointi = intent.getFloatExtra("extra", 0);
        Button b1=findViewById(R.id.b1);
        int x = R.drawable.ic_launcher_background;
        meemi=findViewById(R.id.imageView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.henVointi<100){
                    MainActivity.henVointi*= (float)1.1;
                    MainActivity.weeklyHenVointi*= (float)1.1;
                    MainActivity.alltimeHenVointi*= (float)1.1;
                }
                if (MainActivity.henVointi>=100){
                    MainActivity.henVointi = 100;
                }
            }
        });

        Button b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meemi.setImageResource(images[rand.nextInt(images.length)]);
            }
        });

        Button b3=findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.henVointi>0){
                    MainActivity.henVointi*= (float)0.9;
                    MainActivity.weeklyHenVointi*= (float)0.9;
                    MainActivity.alltimeHenVointi*= (float)0.9;
                }
                if (MainActivity.henVointi<=0){
                    MainActivity.henVointi = 0;
                }


            ;
            }
        });
        meemi.setImageResource(images[rand.nextInt(images.length)]);
    }
}
