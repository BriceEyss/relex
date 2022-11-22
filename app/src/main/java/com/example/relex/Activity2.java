package com.example.relex;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Activity2 extends AppCompatActivity {
    public Button button1, button2;
    public RelativeLayout relativeLayout;



    // runnable function
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // set the background on the screen
            relativeLayout.setBackgroundResource(R.color.green);

            // get the system time in milli second
            // when the screen background is set
            final long time = System.currentTimeMillis();


            // function when stop button is clicked
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((TextView)findViewById(R.id.tvVar1)).setText("");
                    double timeAfter=0;
                    // get the system time in milli second
                    // when the stop button is clicked
                    long time1 = System.currentTimeMillis();
                    timeAfter = time1*0.001-time*0.001;
                    button2.setVisibility(View.GONE);
                    button1.setVisibility(View.VISIBLE);
                    if (timeAfter<= 0.5){
                        relativeLayout.setBackgroundResource(0);
                        // display reflex time in toast message
                        ((TextView)findViewById(R.id.tvVar2)).setText("Vos reflex ont prit " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, vous etes trop rapide");
                        relativeLayout.setBackgroundResource(R.drawable.mister4);
                    }else if (timeAfter<= 0.6){
                        relativeLayout.setBackgroundResource(0);
                        // display reflex time in toast message
                        ((TextView)findViewById(R.id.tvVar2)).setText("Vos reflex ont prit " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, c'est acceptable");
                        relativeLayout.setBackgroundResource(R.drawable.mister1);
                    }else if (timeAfter<= 0.8){
                        relativeLayout.setBackgroundResource(0);
                        // display reflex time in toast message
                        ((TextView)findViewById(R.id.tvVar2)).setText("Tes reflex ont prit " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, c'est bof");
                        relativeLayout.setBackgroundResource(R.drawable.mister2);
                    }else if (timeAfter<= 1){
                        relativeLayout.setBackgroundResource(0);
                        // display reflex time in toast message
                        ((TextView)findViewById(R.id.tvVar3)).setText("Tes reflex ont prit " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, t'as fais expres avoue ?");
                        relativeLayout.setBackgroundResource(R.drawable.mister3);
                    }else if (timeAfter> 1){
                        relativeLayout.setBackgroundResource(0);
                        // display reflex time in toast message
                        ((TextView)findViewById(R.id.tvVar3)).setText("tes reflex ont prit " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, t'es null en sah");
                        relativeLayout.setBackgroundResource(R.drawable.mister5);
                    }
                    // remove the background again
                  //  relativeLayout.setBackgroundResource(0);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button1 = findViewById(R.id.btVar1);
        button2 = findViewById(R.id.btVar2);

        getSupportActionBar().setTitle("Reflex");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        relativeLayout = findViewById(R.id.rlVar1);

        View b1 = findViewById(R.id.btVar1);
        View b = findViewById(R.id.btVar2);
        b.setVisibility(View.GONE);

        /* function when the start button is clicked */
        button1.setOnClickListener(view -> {
            b1.setVisibility(View.GONE);
            b.setVisibility(View.VISIBLE);
            relativeLayout.setBackgroundResource(0);
            ((TextView)findViewById(R.id.tvVar3)).setText("");
            ((TextView)findViewById(R.id.tvVar2)).setText("");
            ((TextView)findViewById(R.id.tvVar1)).setText("Cliquez d'abord sur Start, et attendez jusqu'à ce que la couleur de fond change.\n" +
                    "Dès qu'elle change, cliquez sur Stop");
            // generate a random number from 1-10
            Random random = new Random();
            int num = random.nextInt(10);

            // call the runnable function after
            // a post delay of num seconds
            Handler handler = new Handler();
            handler.postDelayed(runnable, num * 1000);
        });

    }
}