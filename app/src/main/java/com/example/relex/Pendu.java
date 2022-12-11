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

    private LinearLayout ll_true;
    private Button button_play;
    private TextView letter_used;
    private EditText text_type;
    private ImageView IV_hangman;
    private  String word;
    private int found;
    private int error;
    private List<Character> listofletters = new ArrayList<>();
    private boolean win;
    private List<String> listwords = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);

        ll_true =       (LinearLayout)  findViewById(R.id.ll_true);
        button_play =   (Button)        findViewById(R.id.button_play);
        letter_used =   (TextView)      findViewById(R.id.letter_used2);
        text_type =     (EditText)      findViewById(R.id.text_type);
        IV_hangman =    (ImageView)     findViewById(R.id.IV_hangman);

        initGame();
        button_play.setOnClickListener(this);
    }

    private void initGame(){
        word="AAA";//generateword();
        win=false;
        error=0;
        win=false;
        found=0;
        listofletters=new ArrayList<>();
        letter_used.setText("");
        IV_hangman.setBackgroundResource(R.drawable.hangman1);

        ll_true.removeAllViews();

        for (int i = 0; i< word.length(); i++){

            TextView oneLetter = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            ll_true.addView(oneLetter);


        }

    }

    @Override
    public void onClick(View v) {

        String letterfrominput = text_type.getText().toString().toUpperCase();
        text_type.setText("");

        if (letterfrominput.length()>0){
            if(!letteralreadyused(letterfrominput.charAt(0), listofletters)){
                listofletters.add(letterfrominput.charAt(0));
                checkletterintoword(letterfrominput, word);
            }

            //La partie est gagné
            if (found== word.length()){
                win = true;
                createDialog(win);
            }

            //
            // lettrepasdanslemot
            if(!word.contains(letterfrominput)){
                error++;
                setImage(error);
                //NumberLife(error);
            }
            if( error == 6 ){
                win=false;
                createDialog(win);
            }

            //affichage des lettres utilisées
            showallleters(listofletters);
        }

    }

    public boolean letteralreadyused (char a, List<Character> listofletters){

        for(int i=0;i<listofletters.size();i++){
            if(listofletters.get(i)==a){
                Toast.makeText(getApplicationContext(),"Vous avez déja entré cette lettre", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public void checkletterintoword(String letter, String word){

        for (int i=0; i < word.length(); i++){
            if(letter.equals(String.valueOf(word.charAt(i)))){
                TextView tv = (TextView) ll_true.getChildAt(i);
                tv.setText((String.valueOf(word.charAt(i))));
                found++;
            }
        }

    }

    public void showallleters(List<Character> listofletters){
        String chaine = "";

        for (int i = 0; i< listofletters.size(); i++){
            chaine += listofletters.get(i) + " ";
        }
        if(!chaine.equals("")){
            letter_used.setText(chaine);
        }
    }
    public void setImage(int error){
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
    public void NumberLife(int error){
        int NbLife=7-error;
        button_play.setText(NbLife);
    }

    public void createDialog(boolean win){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vous avez gagné =");

        if(!win){
            builder.setTitle("Vous avez perdu !");
            builder.setMessage("Le mot était "+ word);
        }
        builder.setPositiveButton(getResources().getString(R.string.replay), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                initGame();

            }
        });

        builder.create().show();
    }

    public List<String> GetlistOfWords(){

        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(getAssets().open("LISTEMOTS.txt")));
            String line;
            while ((line = buffer.readLine()) !=null){
                listwords.add(line);
            }
            buffer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return listwords;
    }
    public String generateword(){
        listwords = GetlistOfWords();
        int random = (int) (Math.floor(Math.random()*listwords.size()));
        String word = listwords.get(random).trim();
        return word;
    }

}