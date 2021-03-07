package com.example.quizzando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button VaiRegistrazione = (Button) findViewById(R.id.btn_Login);
        Button ChiudiApp = (Button) findViewById(R.id.btn_ChiudiApp);

        VaiRegistrazione.setOnClickListener(v -> {
            Intent vairegistrazione = new Intent(MainActivity.this,Activity_Registrazione.class);
            startActivity(vairegistrazione);
        });

        ChiudiApp.setOnClickListener(v -> {
            finish();
        });
    }
}