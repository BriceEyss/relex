package com.example.relex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class Activity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("8 balls");
        setContentView(R.layout.activity_3);

        // link all the variables with its id
        final ImageView imageView = (ImageView) findViewById(R.id.image_eightBall);
        Button button = (Button) findViewById(R.id.askButton);

        // create an array to store all the images
        final int a[] = {R.drawable.ball1,R.drawable.ball2, R.drawable.ball3, R.drawable.ball4, R.drawable.ball5};

        // ask button's onClick function
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // generate a number using Random() function
                Random random = new Random();
                int x = random.nextInt(4);

                // set the image to the view
                imageView.setImageResource(a[x]);
            }
        });
    }
}