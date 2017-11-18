package br.com.theploy.casamentoamodaantiga;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listName = new ArrayList<>();
    private ArrayList<String> listKids = new ArrayList<>();
    private ArrayList<String> listCities = new ArrayList<>();
    private ArrayList<String> listMoney = new ArrayList<>();

    private TextView name01;
    private TextView name02;
    private TextView name03;
    private TextView city01;
    private TextView city02;
    private TextView city03;
    private TextView mAge;
    private ImageView btnPlay;
    private int age;
    private int auxAge;

    private String nameResult;
    private String kidsResult;
    private String cityResult;
    private String moneyResult;
    private String ageResult;
    private String gender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.activity_main);

        name01 = findViewById(R.id.id_name_01);
        name02 = findViewById(R.id.id_name_02);
        name03 = findViewById(R.id.id_name_03);
        city01 = findViewById(R.id.id_city_01);
        city02 = findViewById(R.id.id_city_02);
        city03 = findViewById(R.id.id_city_03);
        mAge = findViewById(R.id.id_age);
        btnPlay = findViewById(R.id.id_btn_play);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initElements();
                initPlay();
                getResult();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, Main2Result.class);
                        intent.putExtra("name", nameResult);
                        intent.putExtra("kids", kidsResult);
                        intent.putExtra("city", cityResult);
                        intent.putExtra("money", moneyResult);
                        intent.putExtra("age", ageResult);
                        startActivity(intent);
                    }
                },1000);
            }
        });


    }

    public void initPlay() {
        while (listName.size() > 1 || listKids.size() > 1) {

            for (int i = 0; i < listName.size(); i++) {
                if (listName.size() > 1) {
                    age--;
                    if (age == 0) {
                        listName.remove(i);
                        age = auxAge;
                        if (i != 2) {
                            i--;
                        }
                    }
                }
            }

            for (int i = 0; i < listKids.size(); i++) {
                if (listKids.size() > 1) {
                    age--;
                    if (age == 0) {
                        listKids.remove(i);
                        age = auxAge;
                        if (i != 2) {
                            i--;
                        }
                    }
                }
            }

            for (int i = 0; i < listCities.size(); i++) {
                if (listCities.size() > 1) {
                    age--;
                    if (age == 0) {
                        listCities.remove(i);
                        age = auxAge;
                        if (i != 2) {
                            i--;
                        }
                    }
                }
            }

            for (int i = 0; i < listMoney.size(); i++) {
                if (listMoney.size() > 1) {
                    age--;
                    if (age == 0) {
                        listMoney.remove(i);
                        age = auxAge;
                        if (i != 2) {
                            i--;
                        }
                    }
                }
            }
        }
    }

    public void initElements(){
        listName.add(0, name01.getText().toString());
        listName.add(1, name02.getText().toString());
        listName.add(2, name03.getText().toString());

        listCities.add(0, city01.getText().toString());
        listCities.add(1, city02.getText().toString());
        listCities.add(2, city03.getText().toString());

        listKids.add(0,"1");
        listKids.add(1,"2");
        listKids.add(2,"3");

        Bundle extra = getIntent().getExtras();

        gender = extra.getString("gender");

        if(gender.equals("boy")) {
            listMoney.add(0, "Milionário");
            listMoney.add(1, "Rico");
            listMoney.add(2, "Pobre");
        }else {
            listMoney.add(0, "Milionária");
            listMoney.add(1, "Rica");
            listMoney.add(2, "Pobre");
        }

        age = Integer.parseInt(mAge.getText().toString());
        auxAge = age;
    }

    public void getResult(){
        if (listName.size() == 1 && listKids.size() == 1 && listCities.size() == 1 && listMoney.size() == 1) {
            Log.i("Resultado", String.valueOf(listName) + " ---- " + listKids + " ---- " + listCities + " ---- " + listMoney);
        }
        nameResult = listName.get(0);
        kidsResult = listKids.get(0) + " filhos";
        cityResult = listCities.get(0);
        moneyResult = listMoney.get(0);
        ageResult = mAge.getText() + " anos";

        listName.clear();
        listCities.clear();
        listMoney.clear();
        listKids.clear();
    }
}
