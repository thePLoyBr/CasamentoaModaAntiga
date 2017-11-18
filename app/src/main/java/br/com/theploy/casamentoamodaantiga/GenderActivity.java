package br.com.theploy.casamentoamodaantiga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class GenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_gender);

        ImageView mBoy = findViewById(R.id.iv_boy_symbol);
        ImageView mGirl = findViewById(R.id.iv_girl_symbol);

        mBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenderActivity.this, MainActivity.class);
                intent.putExtra("gender", "boy");
                startActivity(intent);
            }
        });

        mGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenderActivity.this, MainActivity.class);
                intent.putExtra("gender", "girl");
                startActivity(intent);
            }
        });

    }
}
