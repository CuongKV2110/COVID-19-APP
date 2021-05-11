package com.developer.arsltech.covid_19tracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewsScreen extends AppCompatActivity {
    Button btnkhaibao;
    TextView myWebSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_screen);
        btnkhaibao = findViewById(R.id.khaibao);

        btnkhaibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openkhaibaoyte();
            }
        });

    }

    public void openkhaibaoyte(){
        Intent intent = new Intent(this, KhaiBaoYTe.class);
        startActivity(intent);
    }
}