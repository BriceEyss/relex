package com.example.relex;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button button, button1, button2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Mini Games");

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> openActivity2());

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(v -> openActivity3());

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(v -> openActivity4());

    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent(this, Pendu.class);
        startActivity(intent);
    }
}