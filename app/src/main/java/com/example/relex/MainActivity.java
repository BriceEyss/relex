package com.example.relex;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button button, button1, button2,button3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Permet de donner au layout principal le "activity_main"
        setContentView(R.layout.activity_main);
        //Permet de mettre le nom Mini Games en haut de la page
        getSupportActionBar().setTitle("Mini Games");

        // Trouve le boutton qui porte l'id button et le recupere pour le mettre dans la variable "button"
        button = (Button) findViewById(R.id.button);
        //Permet d'ouvrir d'appeler la fonction openActivity2
        button.setOnClickListener(v -> openActivity2());

        // Trouve le boutton qui porte l'id button1 et le recupere pour le mettre dans la variable "button1"
        button1 = (Button) findViewById(R.id.button1);
        //Permet d'ouvrir d'appeler la fonction openActivity3
        button1.setOnClickListener(v -> openActivity3());

        // Trouve le boutton qui porte l'id button2 et le recupere pour le mettre dans la variable "button2"
        button2 = (Button) findViewById(R.id.button2);
        //Permet d'ouvrir d'appeler la fonction openActivity4
        button2.setOnClickListener(v -> openActivity4());

        // Trouve le boutton qui porte l'id button3 et le recupere pour le mettre dans la variable "button3"
        button3 = (Button) findViewById(R.id.button3);
        //Permet d'ouvrir d'appeler la fonction openActivity5
        button3.setOnClickListener(v -> openActivity5());

    }

    //Fonction qui permet d'ouvrir l'activité 2 (reflexe)
    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
    //Fonction qui permet d'ouvrir l'activité 3 (8ball)
    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
    //Fonction qui permet d'ouvrir l'activité 4 (pendu)
    public void openActivity4() {
        Intent intent = new Intent(this, Pendu.class);
        startActivity(intent);
    }
    //Fonction qui permet d'ouvrir l'activité 4 (tic tac toe)
    public void openActivity5() {
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }
}