package com.example.relex;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//création de l'activité

public class Activity4 extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3]; //création de la grille

    //le joueur 1 commence à jouer
    private boolean player1Turn = true;

    //variable pour le nombre de points sur la grille
    private int roundCount;

    private int player1Points; //score joueur 1
    private int player2Points; //score joueur 2

    private TextView textViewPlayer1; //affichage score joueur 1
    private TextView textViewPlayer2; //affichage score joueur 2

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) { //à la création de l'Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4); //on crée le Layout de l'Activity

        getSupportActionBar().setTitle("TicTacToe"); //Nom du jeu affiché dans un bandeau
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//bouton arrière intégré de base au bandeau

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        //numérotation des boutons de la grille 3*3
        //création d'un nom pour chaque bouton
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                //création des noms pour les boutons dans le activity_4.xml
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                //le bouton est attribué à une ressource placée sur l'écran
                buttons[i][j] = findViewById(resID);
                //attente d'un évènement "Click" pour toutes les cases de la grille
                buttons[i][j].setOnClickListener(this);
            }
        }

        //création du bouton Reset
        Button buttonReset = findViewById(R.id.button_reset);

        //fonction lorsque l'on appuie sur le bouton reset
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //les deux scores sont remis à 0
                player1Points=0;
                player2Points=0;

                //mise à jour de l'affichage des scores
                updatePointsText();

                //reset de la grille
                resetBoard();
            }
        });
    }

    @Override
    //fonction lorsque l'on appuie sur un bouton de la grille
    public void onClick(View v) {
        //si le bouton a déjà été joué on ne fait rien
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        //si c'est le tour du joueur 1 on met un X  l'emplacement cliqué
        if (player1Turn) {
            ((Button) v).setText("X");

            //si c'est le tour du joueur 2 on met un 0  l'emplacement cliqué
        } else {
            ((Button) v).setText("O");
        }

        //à chaque fois qu'un joueur joue, on incrémente le nombre de coups joués (9 maximum)
        roundCount++;


        //vérification de la victoire ou non d'un des joueurs
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }

            //si pas de victoire
            //si la grille est plein, c'est un match nul
        } else if (roundCount == 9) {
            draw();
        } else {

            //sinon c'est au tour de l'autre joueur
            player1Turn = !player1Turn;
        }

    }


    //fonction pour vérifier si un joueur a gagné
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        //on regarde les valeurs de tous les boutons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //s'il y a la même valeur dans chaque case d'une ligne, victoire
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //s'il y a la même valeur dans chaque case d'une colonne, victoire
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //s'il y a la même valeur dans chaque case de la digonale "haut-gauche"-"bas-droite", victoire
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        //s'il y a la même valeur dans chaque case de la digonale "haut-droite"-"bas-gauche", victoire

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        //sinon il n'y a pas de victoire
        return false;
    }

    //fonction dans le cas où le joueur 1 a gagné
    private void player1Wins() {
        player1Points++; //incrémentation de son nombre de points
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();//affichage toast
        updatePointsText();//mise à jour de l'affichage des scores
        resetBoard(); //remise à zéro de la grille
    }

    //fonction dans le cas où le joueur 2 a gagné
    //idem que pour joueur 1
    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    //fonction dans le cas d'un match nul
    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();//affichage toast
        resetBoard();//remise à zéro de la grille
    }

    //fonction pour mettre à jour l'affichage des scores
    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points); //mise à jour de la vue textViewPlayer1
        textViewPlayer2.setText("Player 2: " + player2Points); //mise à jour de la vue textViewPlayer2
    }


    //remise à zéro de la grille
    //pour toutes les case on supprime le texte
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        //remise à zéro du compteur de tours  la fin de la partie
        roundCount = 0;

        //c'est toujours le joueur 1 qui commence
        player1Turn = true;
    }
}