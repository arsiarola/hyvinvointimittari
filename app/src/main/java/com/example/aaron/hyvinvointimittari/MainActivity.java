
package com.example.aaron.hyvinvointimittari;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/*ideas for project:
  maybe a little minigame if henVointi is low, which could be harder based on how low it is. e.g. tap some stuff on the screen
  , jump over obstacles or something like in the google dinosaurs minigame. could then increase henVoinit
  Display a meme if henVointi is low, could also display smiley if happy, sad face if not etc
  fysVointi needs a parameter to set amount of time you did the exercise. Could later tell you how much you'd need to exercise
  (a specific sport maybe) to fill the fysVointi meter.
  could make most recent emotion to have a bigger impact on meter
  could add all instances of suoritukset and olotilat into an array and have the user be able to access what he/she has done/felt like before
  this way we can also get listview and saving information to the project

 */
public class MainActivity extends AppCompatActivity {
    //Declaring variables
    public static int fysVointi = 0;
    public static float henVointi = 50;
    private int weeklyFysVointi = 0;
    public static float weeklyHenVointi = 50;
    private int alltimeFysVointi = 0;
    public static float alltimeHenVointi = 50;
    private Button suoritusButton;
    private Button userSettings;
    private AutoCompleteTextView oloTilaText;
    private AutoCompleteTextView suoritus;
    private EditText suoritusMaara;
    public static int EXTRA_MESSAGE = 100;
    private ArrayList<olotilat> olot;
    private ArrayList<suoritukset> suoritukset;
    private boolean wrongText = true;
    private boolean textFail = true;
    int recentMultiplier;
    private Button oloTilaButton;
    private MainActivity thisActivity;
    private Button mittariButton;
    private static final String PREF = "DATA";
    private Calendar kalenteri;
    private int previousDate;
    private int day;
    private static String alltimeFys = "alltimeFysVointi";
    private static String alltimeHen = "alltimeHenVointi";
    private static String weeklyHen = "weeklyHenVointi";
    private static String weeklyFys = "weeklyFysVointi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing variables



        mittariButton =  findViewById(R.id.mittari);
        userSettings = findViewById(R.id.userSettings);
        thisActivity = this;
        oloTilaText = findViewById(R.id.olotila);
        oloTilaButton = findViewById(R.id.oloTilaButton);
        suoritus = findViewById(R.id.fysSuoritus);
        suoritusMaara =findViewById(R.id.fysSuoritusMaara);
        suoritusButton = findViewById(R.id.fysSuoritusButton);
        kalenteri = Calendar.getInstance(TimeZone.getTimeZone("EET"));
        kalenteri.setFirstDayOfWeek(Calendar.MONDAY);
        day = kalenteri.get(kalenteri.DAY_OF_WEEK);

        SharedPreferences prefGet = getSharedPreferences(PREF, Activity.MODE_PRIVATE);
        henVointi = prefGet.getFloat("henVointi",50f);
        fysVointi = prefGet.getInt("fysVointi", 0);
        weeklyHenVointi = prefGet.getFloat("weeklyHenVointi", 50f);
        weeklyFysVointi = prefGet.getInt("weeklyFysVointi", 0);
        alltimeFysVointi = prefGet.getInt("alltimeFysVointi", 0);
        alltimeHenVointi = prefGet.getFloat("alltimeHenVointi", 50f);
        previousDate = prefGet.getInt("previousDate", day);
        Log.d("äksdee",previousDate + " ja " + day);
        if(previousDate != day){
            henVointi = 50;
            fysVointi = 0;
            if(day == 2){
                weeklyFysVointi = 0;
                weeklyHenVointi = 50;
            }

        }

        //luodaan eri olotiloja
        olot = new ArrayList<olotilat>();
        olot.add(new olotilat("surullinen", 0.6)); //could add an array of strings instead so same word differently spelled etc works
        olot.add(new olotilat("iloinen", 1.6));
        olot.add(new olotilat("masentunut", 0.3));
        olot.add(new olotilat("itsetuhoinen", 0.1));
        olot.add(new olotilat("hyvä", 1.3));
        olot.add(new olotilat("innostunut", 1.8));
        olot.add(new olotilat("vihainen", 0.7));
        olot.add(new olotilat("ärsyyntynyt", 0.85));
        olot.add(new olotilat("pelko", 0.25));
        olot.add(new olotilat("rakastunut", 2.0));
        olot.add(new olotilat("pettynyt", 0.7));
        olot.add(new olotilat("turhautunut", 0.8));
        olot.add(new olotilat("ahdistunut", 0.8));
        olot.add(new olotilat("kateellinen", 0.7));
        olot.add(new olotilat("häpeä", 0.6));
        olot.add(new olotilat("mukava", 1.5));
        olot.add(new olotilat("epämukava", 2 / 3));
        olot.add(new olotilat("rauhallinen", 1.4));
        olot.add(new olotilat("ylpeä", 1.7));

        //luodaan eri suorituksia
        suoritukset = new ArrayList<suoritukset>();
        suoritukset.add(new suoritukset("juoksu", 50));
        suoritukset.add(new suoritukset("tennis", 30));
        suoritukset.add(new suoritukset("sulkapallo", 25));
        suoritukset.add(new suoritukset("jalkapallo", 30));
        suoritukset.add(new suoritukset("uinti", 40));
        suoritukset.add(new suoritukset("painonnosto", 30));
        suoritukset.add(new suoritukset("tanssi", 20));
        suoritukset.add(new suoritukset("jääkiekko", 40));
        suoritukset.add(new suoritukset("sprintti", 55));
        suoritukset.add(new suoritukset("kävely", 15));
        suoritukset.add(new suoritukset("keihäänheitto", 25));
        suoritukset.add(new suoritukset("hiihto", 45));
        suoritukset.add(new suoritukset("koripallo", 40));
        suoritukset.add(new suoritukset("sähly", 40));
        suoritukset.add(new suoritukset("lentopallo", 40));
        suoritukset.add(new suoritukset("rullaluistelu", 35));
        suoritukset.add(new suoritukset("pyöräily, kevyt", 30));
        suoritukset.add(new suoritukset("pyöräily, raskas", 50));
        suoritukset.add(new suoritukset("kamppailu", 45));
        suoritukset.add(new suoritukset("laskettelu", 40));
        suoritukset.add(new suoritukset("moottoriurheilu", 15));
        suoritukset.add(new suoritukset("pesäpallo", 40));
        suoritukset.add(new suoritukset("venyttely", 10));

        //luodaan olotila- ja suoritusvaihtoehdoista nimilistat
        ArrayList<String> suoritusNimet = new ArrayList<String>();
        for (int i = 0; i < suoritukset.size(); i++) {
            suoritusNimet.add(suoritukset.get(i).getOlotila());
        }
        ArrayList<String> olotilaNimet = new ArrayList();
        for (int i = 0; i < olot.size(); i++) {
            olotilaNimet.add(olot.get(i).getOlotila());
        }

        //luodaan lista kirjoitettavista vaihtoehdoista
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, suoritusNimet);
        suoritus.setThreshold(1); //will start working from first character
        suoritus.setAdapter(adapter);

        //luodaan lista kirjoitettavista vaihtoehdoista
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, olotilaNimet);
        oloTilaText.setThreshold(1); //will start working from first character
        oloTilaText.setAdapter(adapter2);

        //Lisää uuden suorituksen
        suoritusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongText = true; //muuttuja ilmaisee onko teksti väärä
                textFail = false; //muuttuja ilmaisee onko kentässä numero
                double time = 0;
                try { //yrittää muuttaa syötteen numeroksi
                    time = Double.parseDouble(suoritusMaara.getText().toString());
                } catch (NumberFormatException e) {
                    textFail = true;
                }
                if (!textFail) {
                    for (int i = 0; i < suoritukset.size(); i++) {
                        if (suoritus.getText().toString().equals(suoritukset.get(i).getOlotila())) {
                            if(fysVointi <=100){
                                fysVointi += time * suoritukset.get(i).getMultiplier();
                                weeklyFysVointi += time * suoritukset.get(i).getMultiplier();
                                alltimeFysVointi += time * suoritukset.get(i).getMultiplier();
                            }
                            if(fysVointi>100){
                                fysVointi = 100;
                            }
                            suoritus.setText("");
                            wrongText = false;
                            break;
                        }
                    }
                }

                if (wrongText) {
                    suoritus.setText("virhe");
                }
            }
        });


        //lisää uuden olotilan
        oloTilaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongText = true;
                for (int i = 0; i < olot.size(); i++) {
                    if (oloTilaText.getText().toString().equals(olot.get(i).getOlotila())) {
                        if(henVointi <=100){
                            henVointi *= olot.get(i).getMultiplier();
                            weeklyHenVointi *= olot.get(i).getMultiplier();
                            alltimeHenVointi *= olot.get(i).getMultiplier();
                        }
                        if (Math.round(henVointi) == 0) {
                            henVointi = 1;
                        }else if(henVointi >100){
                            henVointi=100;
                        }
                        if (Math.round(weeklyHenVointi) == 0) {
                            weeklyHenVointi = 1;
                        }
                        if (Math.round(alltimeHenVointi) == 0) {
                            alltimeHenVointi = 1;
                        }
                        oloTilaText.setText("");
                        suoritusMaara.setText("");
                        wrongText = false;
                        break;
                    }
                }
                if (wrongText == true) {
                    oloTilaText.setText("virhe");
                }
            }
        });

        //siirtyy MittariActivityyn ja tuo mukanaan henVoinnin ja fysVoinnin määrän
        mittariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, MittariActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                intent.putExtra("extra", henVointi);
                intent.putExtra("fysVointi", fysVointi);
                intent.putExtra(weeklyFys, weeklyFysVointi);
                intent.putExtra(weeklyHen, weeklyHenVointi);
                intent.putExtra(alltimeHen,alltimeHenVointi);
                intent.putExtra(alltimeFys,alltimeFysVointi);
                startActivity(intent);
            }
        });

        //placeholder
        userSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, meemi.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                startActivity(intent);
            }
        });


    }

    public void onPause() {
        //tallentaa tietoja
        SharedPreferences prefPut = getSharedPreferences(PREF, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putFloat("henVointi", henVointi);
        prefEditor.putInt("fysVointi", fysVointi);
        prefEditor.putInt(weeklyFys, weeklyFysVointi);
        prefEditor.putFloat(weeklyHen, weeklyHenVointi);
        prefEditor.putInt(alltimeFys, alltimeFysVointi);
        prefEditor.putFloat(alltimeHen, alltimeHenVointi);
        prefEditor.putInt("previousDate", kalenteri.get(kalenteri.DAY_OF_WEEK));
        prefEditor.commit();
        super.onPause();
    }
}
