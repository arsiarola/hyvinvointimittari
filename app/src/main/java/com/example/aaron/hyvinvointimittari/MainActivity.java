
package com.example.aaron.hyvinvointimittari;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Tällä activitylla listätään henkisen- ja fyysisenhyvinvoinnin liittyviä toimenpitetiä
 */
public class MainActivity extends AppCompatActivity {
    //Declaring variables
    private int fysVointi = 0;
    private float henVointi = 50;
    private int weeklyFysVointi = 0;
    private float weeklyHenVointi = 50;
    private int alltimeFysVointi = 0;
    private float alltimeHenVointi = 50;
    private Button suoritusButton;
    private Button meemiButton;
    private AutoCompleteTextView oloTilaText;
    private AutoCompleteTextView suoritus;
    private EditText suoritusMaara;
    private ArrayList<olotilat> olot;
    private ArrayList<suoritukset> suoritukset;
    private boolean wrongText = true;
    private boolean textFail = true;
    private Button oloTilaButton;
    private MainActivity thisActivity;
    private Button mittariButton;
    public static final String PREF = "DATA";
    private Calendar kalenteri;
    private int previousDate;
    private int day;
    private int numberOfDays = 1;
    public final static String alltimeFys = "alltimeFysVointi";
    public final static String alltimeHen = "alltimeHenVointi";
    public final static String weeklyHen = "weeklyHenVointi";
    public final static String weeklyFys = "weeklyFysVointi";
    public final static String oldallfys = "oldallfys";
    public final static String oldallhen = "oldallhen";
    public final static String oldweekfys = "oldweekfys";
    public final static String oldweekhen = "oldweekhen";
    private int vanhaWeeklyFysVointi;
    private float vanhaWeeklyHenVointi;
    private int vanhaAlltimeFysVointi;
    private float vanhaAllTimeHenVointi;

    /**
     * Alustetaan buttonit ja muuttujat, listätään niihin logiikka.
     * Olotilojen ja suorituksien luominen, jotka lisätään omiin arraylisteihinsä
     * laskee kuluneet päivät joiden avulla voidaan määrittää onko viikko jo kulunut
     * tehdään olotila- ja suorituslistoista uudet nimilistat joita käytetään Autocompleteview:n
     * kanssa
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing variables
        //checkboxit
        final CheckBox checkBoxFys = findViewById(R.id.checkBoxFys);
        final CheckBox checkBoxHenk = findViewById(R.id.checkBoxHenk);
        checkBoxFys.setText("");
        checkBoxHenk.setText("");
        checkBoxHenk.setChecked(false);
        checkBoxFys.setChecked(false);
        mittariButton =  findViewById(R.id.mittari);
        meemiButton = findViewById(R.id.meemiButton);
        thisActivity = this;
        oloTilaText = findViewById(R.id.olotila);
        oloTilaButton = findViewById(R.id.oloTilaButton);
        suoritus = findViewById(R.id.fysSuoritus);
        suoritusMaara =findViewById(R.id.fysSuoritusMaara);
        suoritusButton = findViewById(R.id.fysSuoritusButton);
        kalenteri = Calendar.getInstance(TimeZone.getTimeZone("EET"));
        kalenteri.setFirstDayOfWeek(Calendar.MONDAY);
        day = kalenteri.get(kalenteri.DAY_OF_WEEK);



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
            suoritusNimet.add(suoritukset.get(i).getSuoritus());
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
                        if (suoritus.getText().toString().equals(suoritukset.get(i).getSuoritus())) {
                            if(fysVointi <100){
                                fysVointi += time * suoritukset.get(i).getMultiplier();
                                weeklyFysVointi += time * suoritukset.get(i).getMultiplier();
                                alltimeFysVointi += time * suoritukset.get(i).getMultiplier();
                            }//5
                            if(fysVointi>=100){
                                fysVointi = 100;
                                weeklyFysVointi = vanhaWeeklyFysVointi +100;
                                alltimeFysVointi = vanhaAlltimeFysVointi+100;
                            }
                            suoritus.setText("");
                            suoritusMaara.setText("");
                            wrongText = false;
                            break;
                        }
                    }
                }

                if (wrongText) {
                    suoritus.setText("virhe");

                }
                /// listätty checkbox toiminta

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if(wrongText) {
                            checkBoxFys.setText("Ei lisätty");
                            checkBoxFys.setChecked(false);
                        } else {
                            checkBoxFys.setText("Lisätty");
                            checkBoxFys.setChecked(true);
                        }
                }

                    public void onFinish() {
                        checkBoxFys.setText("");
                        checkBoxFys.setChecked(false);
                    }
                }.start();

            }
        });
        //tyhjentää tiedot
        suoritus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suoritus.setText("");
            }
        });

        //lisää uuden olotilan
        oloTilaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongText = true;
                for (int i = 0; i < olot.size(); i++) {
                    if (oloTilaText.getText().toString().equals(olot.get(i).getOlotila())) {
                        weeklyHenVointi += -henVointi + henVointi * olot.get(i).getMultiplier();
                        alltimeHenVointi += -henVointi + henVointi*olot.get(i).getMultiplier();
                        henVointi *= olot.get(i).getMultiplier();

                        if (Math.round(henVointi) == 0) {
                            henVointi = 1;
                        }else if(henVointi >=100){
                            henVointi=100;
                            alltimeHenVointi = vanhaAllTimeHenVointi+100;
                            weeklyHenVointi = vanhaWeeklyHenVointi + 100;
                        }
                        if (Math.round(weeklyHenVointi) == 0) {
                            weeklyHenVointi = vanhaAllTimeHenVointi+1;
                        }
                        if (Math.round(alltimeHenVointi) == 0) {
                            alltimeHenVointi = vanhaAllTimeHenVointi+1;
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
                /// listätty checkbox toiminta olotiloihi

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if(wrongText) {
                            checkBoxHenk.setText("Ei lisätty");
                            checkBoxHenk.setChecked(false);
                        } else {
                            checkBoxHenk.setText("Lisätty");
                            checkBoxHenk.setChecked(true);
                        }
                    }

                    public void onFinish() {
                        checkBoxHenk.setText("");
                        checkBoxHenk.setChecked(false);
                    }
                }.start();
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
                intent.putExtra("päiviä",numberOfDays);
                startActivity(intent);
            }
        });
        meemiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, meemi.class);
                intent.putExtra("extra", henVointi);
                intent.putExtra("fysVointi", fysVointi);
                intent.putExtra(weeklyFys, weeklyFysVointi);
                intent.putExtra(weeklyHen, weeklyHenVointi);
                intent.putExtra(alltimeHen,alltimeHenVointi);
                intent.putExtra(alltimeFys,alltimeFysVointi);
                intent.putExtra("vanhaWeekHen",vanhaWeeklyHenVointi);
                intent.putExtra("vanhaWeekFys",vanhaWeeklyFysVointi);
                intent.putExtra("vanhaAllHen",vanhaAllTimeHenVointi);
                intent.putExtra("vanhaAllFys",vanhaAlltimeFysVointi);
                startActivity(intent);
            }
        });
    }
    /**
     * Kun kutsutaan onPause methodia tallentaan dataa
     */
    public void onPause() {
        //tallentaa tietoa
        SharedPreferences prefPut = getSharedPreferences(PREF, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putFloat("henVointi", henVointi);
        prefEditor.putInt("fysVointi", fysVointi);
        prefEditor.putInt(weeklyFys, weeklyFysVointi);
        prefEditor.putFloat(weeklyHen, weeklyHenVointi);
        prefEditor.putInt(alltimeFys, alltimeFysVointi);
        prefEditor.putFloat(alltimeHen, alltimeHenVointi);
        prefEditor.putInt("previousDate", kalenteri.get(kalenteri.DAY_OF_WEEK));
        prefEditor.putInt("numberOfDays", numberOfDays);
        prefEditor.putFloat(oldallhen, vanhaAllTimeHenVointi);
        prefEditor.putInt(oldweekfys,vanhaWeeklyFysVointi);
        prefEditor.putFloat(oldweekhen,vanhaWeeklyHenVointi);
        prefEditor.putInt(oldallfys,vanhaAlltimeFysVointi);
        prefEditor.commit();
        super.onPause();
    }

    /**
     * Kutsuttaessa onResume methodia haetaan tiedot jotka tallennettiin onPause methodissa
     */
    public void onResume(){
        SharedPreferences prefGet = getSharedPreferences(PREF, Activity.MODE_PRIVATE);
        henVointi = prefGet.getFloat("henVointi",50f);
        fysVointi = prefGet.getInt("fysVointi", 0);
        weeklyHenVointi = prefGet.getFloat("weeklyHenVointi", 50f);
        weeklyFysVointi = prefGet.getInt("weeklyFysVointi", 0);
        alltimeFysVointi = prefGet.getInt("alltimeFysVointi", 0);
        alltimeHenVointi = prefGet.getFloat("alltimeHenVointi", 50f);
        numberOfDays = prefGet.getInt("numberOfDays", 1);
        previousDate = prefGet.getInt("previousDate", day);
        vanhaAllTimeHenVointi = prefGet.getFloat(oldallhen, vanhaAllTimeHenVointi);
        vanhaWeeklyFysVointi =  prefGet.getInt(oldweekfys,vanhaWeeklyFysVointi);
        vanhaWeeklyHenVointi = prefGet.getFloat(oldweekhen,vanhaWeeklyHenVointi);
        vanhaAlltimeFysVointi= prefGet.getInt(oldallfys,vanhaAlltimeFysVointi);
        //katsotaan onko päivä muuttunut
        if(previousDate != day){
            vanhaAlltimeFysVointi = alltimeFysVointi;
            vanhaWeeklyFysVointi = weeklyFysVointi;
            vanhaWeeklyHenVointi = weeklyHenVointi+50;
            vanhaAllTimeHenVointi = alltimeHenVointi+50;
            numberOfDays++;
            henVointi = 50;
            fysVointi = 0;
            if(numberOfDays%7 == 0){
                weeklyFysVointi = 0;
                weeklyHenVointi = 50;
            }
        }
        super.onResume();
    }
}
