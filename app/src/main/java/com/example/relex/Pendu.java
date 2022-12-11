package com.example.relex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pendu extends AppCompatActivity implements View.OnClickListener {

    private ImageView IV_hangman;//Déclaration d'une nouvelle image de type ImageView
    private LinearLayout ll_true;//Déclaration d'un nouveau champs de type LinearView, qui servira de champs dans lequel nous crérront un nombre de TextView correspondant aux nombres de lettres du mot, et qui comporterons des traits  et les lettres devinées
    private EditText text_type;//Déclaration d'un nouveau champs d'edition de texte de type EditText
    private Button button_play;//Déclaration d'un nouveau boutton de type Button
    private TextView letter_used;//Déclaration d'un nouveau champs texte de type TextView, qui permettra d'afficher les lettres déja utilisées

    private String word;//Déclaration de la String qui comportera le mot à deviner
    private int found;//Déclaration d'un nombre qui servira à compter le nombre de lettres trouvées, qui sera comparé aux nombre de lettre de réel afin de savoir si on a trouvé le mot
    private int error;//Déclaration d'un nombre qui servira à commpter le nombre d'erreur, et qui sera comparé aux nombre de vie restante pour savoir quand on perd
    private List<Character> listofletters = new ArrayList<>();//Déclaration de la liste qui comporteras les lettres déja utilisées
    private boolean win;//Déclaration du bolleen vérifiant la victoire/défaite
    private List<String> listwords = new ArrayList<>();//Déclaration de la liste de mot


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Hangman");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_pendu);

        //Connexion des champs créé avec les champs auxquelles ils correspondent dans activity_pendu.xml :
        ll_true =       (LinearLayout)  findViewById(R.id.ll_true);
        button_play =   (Button)        findViewById(R.id.button_play);
        letter_used =   (TextView)      findViewById(R.id.letter_used2);
        text_type =     (EditText)      findViewById(R.id.text_type);
        IV_hangman =    (ImageView)     findViewById(R.id.IV_hangman);

        initGame();//fonction d'initialisation du jeu, avec remise de toutes les valeurs à 0 et choix du nouveau mot random
        button_play.setOnClickListener(this);//récupération du premier appuis sur le bouton afin de lancer le programme
    }

    private void initGame(){
        word= getrandomword();//word prend la valeur du nouveau mot récupéré aléatoirement par la fonction getradomword()
        error=0;
        win=false;
        found=0;
        listofletters=new ArrayList<>();
        letter_used.setText("");
        IV_hangman.setBackgroundResource(R.drawable.hangman1);//remise en place de l'image de départ du pendu
        button_play.setText("Nombre de vie");//remise en place de la phrase nombre de vie avant qu'elles soient décomptées

        ll_true.removeAllViews();//retirer toutes les champs/vues créer en fonction du nombre de lettre dans le mot.

        for (int i = 0; i< word.length(); i++){//création du nombres nescessaire de vue TextView requit afin de créer tous les traits/espaces que l'on utilisera pour les lettres a trouver

            TextView oneLetter = (TextView) getLayoutInflater().inflate(R.layout.textview, null);//création d'un TextView à partir d'un layout textview.xml déja parametré
            ll_true.addView(oneLetter);//ajout d'une vue à la liste en fonction du nombre de lettre


        }

    }

    @Override
    public void onClick(View v) {

        String letterfrominput = text_type.getText().toString().toUpperCase();//récuppération de la lettre écrite dans text_type et convertissement de celle ci en majuscule au cas ou elle ne le serait pas, afin d'etre comparé plus tard a la liste de lettre du mot
        text_type.setText("");//mise à 0 de la lettre ecrite par l'utilisateur

        if (letterfrominput.length()>0){//empeche de ne rien écrire avant d'executer la fonction
            if(!letteralreadyused(letterfrominput.charAt(0), listofletters)){//vérifies que la lettre n'est pas déja utilisé avec la fonction letteralreadyused
                listofletters.add(letterfrominput.charAt(0));//ajout de la lettre utilisées à l'historique créé par la liste listofletters
                checkletterintoword(letterfrominput, word);//utilisationn de la fonction checkletterintoword() afin de vérifier si la lettre écrite est contenu dans le mot cherché
            }

            //La partie est gagné
            if (found== word.length()){//si le nombre de lettre trouvées found correspond à la taille du mot word alors on entre dans le if
                win = true;//changement de l'état du booleen
                createDialog(win);//on rentre dans la fonction createdialog avec win pour lancer la boitre de dialog victoire et le boutton rejouer
            }

            // lettrepasdanslemot
            if(!word.contains(letterfrominput)){//si la lettre n'est pas contenu dans le mot en rentre dans le if
                error++;//rajout d'une erreur
                setImage(error);//mettre a jour l'image du pendu avec une incrémentation d'une erreur
                NumberLife(error);//mettre a jour le nombre de vie du joueur
            }
            if( error == 6 ){//si le nombre d'erreur corespond au nombre d'erreur permise par le jeu alors on rentre dans le if
                win=false;//le booleen est mis en false
                createDialog(win);//création d'une boite de dialog avec perdu, le mot recherché et le boutton rejouer, et ce grace a la fonction createDialog()
            }

            //affichage des lettres utilisées
            showallleters(listofletters);//on rentre dans la fonction showallleters afin d'afficher la liste de lettre deja utilisées
        }

    }

    public boolean letteralreadyused (char a, List<Character> listofletters){//vérification des lettres déja utilisé en analysant la liste listofletters et la lettre en input

        for(int i=0;i<listofletters.size();i++){//fonction qui permet de comparer avec toutes les lettres contenu dans la liste
            if(listofletters.get(i)==a){//permet de vérifier une lettre déja utilisées, et si oui alors on rentre dans le if
                Toast.makeText(getApplicationContext(),"Vous avez déja entré cette lettre", Toast.LENGTH_SHORT).show();//on affiche pendant un court instant un message pour indiquer une lettre déja utilisées
                return true;
            }
        }
        return false;
    }

    public void checkletterintoword(String letter, String word){//verifie que la lettre entré est présente ou nom dans le mot cherché

        for (int i=0; i < word.length(); i++){//permet de vérifier pour toute les lettres du mot
            if(letter.equals(String.valueOf(word.charAt(i)))){//vérifies la similitude entre la lettre entrée et les lettres du mot
                TextView tv = (TextView) ll_true.getChildAt(i);//appelle le TextView contenant le trais dans lequel la lettre doit aller et l'associer à l avariable tv
                tv.setText((String.valueOf(word.charAt(i))));//assigne la lettre entré au TextView à la bonne position
                found++;//incrémenter de 1 le found
            }
        }

    }

    public void showallleters(List<Character> listofletters){//affiche les lettres déja utilisées sur l'interface
        String chaine = "";

        for (int i = 0; i< listofletters.size(); i++){
            chaine += listofletters.get(i) + " ";//ajoutes les lettres deja utilisées a une string chaine
        }
        if(!chaine.equals("")){
            letter_used.setText(chaine);//permet d'afficher la liste des lettres déja utilisées dans l'interface
        }
    }
    public void setImage(int error){//permet de choisir l'image du pendu à afficher en fonction du nombre d'erreur
        switch(error){
            case 1 :
                IV_hangman.setBackgroundResource(R.drawable.hangman2);
                break;
            case 2 :
                IV_hangman.setBackgroundResource(R.drawable.hangman3);
                break;
            case 3 :
                IV_hangman.setBackgroundResource(R.drawable.hangman4);
                break;
            case 4 :
                IV_hangman.setBackgroundResource(R.drawable.hangman5);
                break;
            case 5 :
                IV_hangman.setBackgroundResource(R.drawable.hangman6);
                break;
            case 6 :
                IV_hangman.setBackgroundResource(R.drawable.hangman7);
                break;
        }
    }

    public void NumberLife(int error){//permet d'afficher le nombre de vie restante en fonction du nombre d'erreur dans le bouton
        int NbLife=6-error;
        String Nblifestring=Integer.toString(NbLife);
        button_play.setText(Nblifestring);
    }

    public void createDialog(boolean win){//boite de dialogue utiliser pour afficher la victoire/la perte ...

        AlertDialog.Builder builder = new AlertDialog.Builder(this);//création de builder du type AlertDialog.Builder donc boite de dialogue
        builder.setTitle("Vous avez gagné =");//titre en cas de victoire

        if(!win){
            builder.setTitle("Vous avez perdu !");//titre en cas de perte
            builder.setMessage("Le mot était "+ word);//mot a trouver en cas de perte
        }
        builder.setPositiveButton(getResources().getString(R.string.replay), new DialogInterface.OnClickListener() {//création du boutton rejouer dans la boite de dialogue
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {//détection du click

                initGame();//retour a la fonction d'initialisation du jeu

            }
        });

        builder.create().show();//création de la boite de dialogue
    }


    public List<String> GetlistOfWords(){
        try {// block de code a tester, donc permet de détecter les erreurs si il y en a
            BufferedReader buffer = new BufferedReader(new InputStreamReader(getAssets().open("pendu_liste.txt")));//Mise de la liste de mot dans un buffer afin d'etre lu efficacement par le programme
            String line;
            while ((line = buffer.readLine()) !=null){//lecture ligne par ligne de la liste de mot et ajout de chaque mot à la liste listwords
                listwords.add(line);
            }
            buffer.close();//fermeture du buffer/libération de la mémoire
        } catch (IOException e){// a exécuter en cas d'erreur dans le try
            e.printStackTrace();//impression de l'erreur si détecté dans try
        }
        return listwords;
    }

    public String getrandomword(){//fonction de récupération d'un mot aléatoirement selon une liste définit
        listwords = GetlistOfWords();//la liste listwords récupère grace à la fonction  GetListOfWords une liste de mot
        int random = (int) (Math.floor(Math.random()*listwords.size()));//creation d'un nombre entier aléatoire en fonction du nombre de mot, qui permettra de choisir le mot aléatoirement
        return listwords.get(random).trim();//retour du mot choisi en fonction de l'entier random
    }

}