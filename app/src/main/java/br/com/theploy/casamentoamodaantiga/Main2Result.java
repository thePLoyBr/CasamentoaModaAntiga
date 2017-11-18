package br.com.theploy.casamentoamodaantiga;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Result extends AppCompatActivity {

    File imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main2_result);

        final ImageView btnPlayAgain = findViewById(R.id.iv_play_again);
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Result.this, GenderActivity.class));
            }
        });

        final ImageView btnShare = findViewById(R.id.iv_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnShare.setVisibility(View.INVISIBLE);
//                btnPlayAgain.setVisibility(View.INVISIBLE);
                final Bitmap bitmap = takeScreenshot();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveBitmap(bitmap);
                    }
                },2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shareIt();
                    }
                },2000);

//                btnShare.setVisibility(View.VISIBLE);
//                btnPlayAgain.setVisibility(View.VISIBLE);
            }
        });


        TextView mName = findViewById(R.id.tv_name);
        TextView mKids = findViewById(R.id.tv_kids);
        TextView mCity = findViewById(R.id.tv_city);
        TextView mMoney = findViewById(R.id.tv_money);
        TextView mAge = findViewById(R.id.tv_age_result);


        Bundle extra = getIntent().getExtras();
        String name = extra.getString("name");
        String kids = extra.getString("kids");
        String city = extra.getString("city");
        String money = extra.getString("money");
        String age = extra.getString("age");

        mName.setText(name);
        mKids.setText(kids);
        mCity.setText(city);
        mMoney.setText(money);
        mAge.setText(age);

    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "APP Casamento a moda Antiga";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Com quem vou me casar S2");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Escolha onde Compartilhar"));
    }
}
