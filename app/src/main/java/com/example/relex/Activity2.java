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

    // fonction exécutable
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // définir l'arrière-plan de l'écran
            relativeLayout.setBackgroundResource(R.color.green);

            // obtenir l'heure système en millisecondes
            // lorsque l'arrière-plan vert de l'écran est défini
            final long time = System.currentTimeMillis();

            // fonction lorsque le bouton d'arrêt est cliqué
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((TextView)findViewById(R.id.tvVar1)).setText("");
                    double timeAfter=0;
                    // obtenir l'heure système en millisecondes
                    // lorsque le bouton d'arrêt est cliqué
                    long time1 = System.currentTimeMillis();

                    // passe le temps en seconde
                    timeAfter = time1*0.001-time*0.001;
                    // fait disparaitre bouton stop
                    button2.setVisibility(View.GONE);
                    // fait apparaitre bouton start
                    button1.setVisibility(View.VISIBLE);

                    // à l'aide du temps mesuré et de la boucle if on fera apparaitre sur l'écran un texte avec
                    // le temps et un message personnalisé
                    if (timeAfter<= 0.5){
                        // Permet de supprimer le background
                        relativeLayout.setBackgroundResource(0);
                        // Afficher le temps de réflexe dans le message toast et dans la textview
                        ((TextView)findViewById(R.id.tvVar2)).setText("Vos réflexes ont pris " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, vous etes trop rapide");
                        // Change le fond d'écran qui correspond au temps
                        relativeLayout.setBackgroundResource(R.drawable.mister4);
                    }else if (timeAfter<= 0.6){
                        // Permet de supprimer le background
                        relativeLayout.setBackgroundResource(0);
                        // afficher le temps de réflexe dans le message toast et dans la textview
                        ((TextView)findViewById(R.id.tvVar2)).setText("Vos réflexes ont pris " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, c'est acceptable");
                        // Change le fond d'écran qui correspond au temps
                        relativeLayout.setBackgroundResource(R.drawable.mister1);
                    }else if (timeAfter<= 0.8){
                        // Permet de supprimer le background
                        relativeLayout.setBackgroundResource(0);
                        // afficher le temps de réflexe dans le message toast et dans la textview
                        ((TextView)findViewById(R.id.tvVar2)).setText("Tes réflexes ont pris " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, c'est bof");
                        // Change le fond d'écran qui correspond au temps
                        relativeLayout.setBackgroundResource(R.drawable.mister2);
                    }else if (timeAfter<= 1){
                        //permet de supprimer le background
                        relativeLayout.setBackgroundResource(0);
                        // afficher le temps de réflexe dans le message toast et dans la textview
                        ((TextView)findViewById(R.id.tvVar3)).setText("Tes réflexes ont pris " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, t'as fais expres avoue ?");
                        // Change le fond d'écran qui correspond au temps
                        relativeLayout.setBackgroundResource(R.drawable.mister3);
                    }else if (timeAfter> 1){
                        // permet de supprimer le background
                        relativeLayout.setBackgroundResource(0);
                        // afficher le temps de réflexe dans le message toast et dans la textview
                        ((TextView)findViewById(R.id.tvVar3)).setText("Tes réflexes ont pris " + Float.parseFloat(String.format("%.5f",timeAfter)) + " secondes, t'es null en sah");
                        // Change le fond d'écran qui correspond au temps
                        relativeLayout.setBackgroundResource(R.drawable.mister5);
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Permet d'avoir le layout de l'activité2
        setContentView(R.layout.activity_2);
        button1 = findViewById(R.id.btVar1); //boutton start
        button2 = findViewById(R.id.btVar2); //boutton stop

        // Permet de définir le nom de l'app en haut de la page
        getSupportActionBar().setTitle("Reflex");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        relativeLayout = findViewById(R.id.rlVar1);

        View b1 = findViewById(R.id.btVar1);
        View b = findViewById(R.id.btVar2);
        // Fait disparaitre le boutton stop à l'ouverture du mini jeu
        b.setVisibility(View.GONE);

        // Fonction lorsque le bouton Start est cliqué
        button1.setOnClickListener(view -> {
            // On cache le boutton Start
            b1.setVisibility(View.GONE);
            // On fait apparaitre le boutton Stop
            b.setVisibility(View.VISIBLE);
            //on supprime le background pour enlever les écritures du démarrage
            relativeLayout.setBackgroundResource(0);
            // Cree des textview invisible qui seront utilisé pour mettre le resultat du reflexe
            ((TextView)findViewById(R.id.tvVar3)).setText("");
            ((TextView)findViewById(R.id.tvVar2)).setText("");
            // Message du début
            ((TextView)findViewById(R.id.tvVar1)).setText("Cliquez d'abord sur Start, et attendez jusqu'à ce que la couleur de fond change.\n" +
                    "Dès qu'elle change, cliquez sur Stop");
            // Génère un nombre aléatoire entre 1-10
            Random random = new Random();
            int num = random.nextInt(10);

            // appel la fonction exécutable après
            // un post-délai de num secondes
            // Permet d'éviter que le fond devienne vert à un temps precis donc on utilise un aléatoire
            Handler handler = new Handler();
            handler.postDelayed(runnable, num * 1000);
        });

    }
}