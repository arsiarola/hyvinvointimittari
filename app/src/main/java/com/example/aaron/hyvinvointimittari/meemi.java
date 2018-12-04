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
    private final int[]  images = {R.drawable.yks, R.drawable.kaks, R.drawable.kol,R.drawable.nel,R.drawable.viis,R.drawable.kuus,R.drawable.seiska,R.drawable.kasi,R.drawable.ysi,R.drawable.kymppi,R.drawable.ykstoist,R.drawable.kakstoist,R.drawable.koltoist,R.drawable.neltoist,R.drawable.viistoist,R.drawable.kuustoist,R.drawable.seitsemantoist,R.drawable.kaheksantoist,R.drawable.yheksantoist,R.drawable.kakskyt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meemi);
        Intent intent = getIntent();
        float henVointi = intent.getFloatExtra("extra", 0);
        Button b1=findViewById(R.id.b1);
        int x = R.drawable.ic_launcher_background;
        meemi=findViewById(R.id.imageView);

        random = rand.nextInt(19) + 0;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          MainActivity.henVointi*= (float)1.1;
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
            MainActivity.henVointi*= (float)0.9;
            }
        });
        meemi.setImageResource(images[rand.nextInt(images.length)]);
    }
}
