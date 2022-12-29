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

        // Permet d'avoir le nom du mini jeu en haut de la page
        getSupportActionBar().setTitle("8 balls");
        // Permet d'avoir le layout de l'activité3
        setContentView(R.layout.activity_3);

        // lie toutes les variables avec son identifiant
        final ImageView imageView = (ImageView) findViewById(R.id.image_eightBall);
        Button button = (Button) findViewById(R.id.askButton);

        // crée un tableau pour stocker toutes les images
        final int a[] = {R.drawable.ball1,R.drawable.ball2, R.drawable.ball3, R.drawable.ball4, R.drawable.ball5};

        // fonction onClick du bouton "appuyez"
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // générer un nombre en utilisant la fonction Random() pour choisir l'image
                Random random = new Random();
                int x = random.nextInt(4);

                // choisi l'image grace au random
                imageView.setImageResource(a[x]);
            }
        });
    }
}