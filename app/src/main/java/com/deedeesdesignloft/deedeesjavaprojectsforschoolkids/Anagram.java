package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Anagram extends AppCompatActivity {
    private EditText editTextEnterStringAnagramWord1, editTextEnterStringAnagramWord2,
            editTextWordEnteredAnagramGame;
    private Button btnCalculateAnagram, btnResetAnagram, btnValidateAnagramGame, btnNewGameAnagramGame,
    btnGetCode;
    private TextView textViewResultAnagram, textViewWordAnagramGame;
    private String wordToFind, codeAnagram;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.main_actionbar, null));

        btnCalculateAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEnterStringAnagramWord1.getText().toString().isEmpty() ||
                        editTextEnterStringAnagramWord2.getText().toString().isEmpty()) {
                    Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.input_something),Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                } else {
                    getAnagram();
                    textViewResultAnagram.setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnResetAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEnterStringAnagramWord1.getText().clear();
                editTextEnterStringAnagramWord2.getText().clear();
                textViewResultAnagram.setText("");
                textViewResultAnagram.setTextColor(getColor(R.color.colorBlack));
                editTextEnterStringAnagramWord1.requestFocus();
                textViewResultAnagram.setVisibility(View.INVISIBLE);
            }
        });

        btnValidateAnagramGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextWordEnteredAnagramGame.getText().toString().isEmpty()) {
                    Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.input_something),Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                } else {
                    validate();
                    //btnValidateAnagramGame.setEnabled(false);
                    textViewWordAnagramGame.setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnNewGameAnagramGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(Anagram.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeAnagram", codeAnagram);
                startActivity(intentCode);

            }
        });


    }

    private void getAnagram(){
        String word1, word2;
        int found, not_found = 0;

        word1 = editTextEnterStringAnagramWord1.getText().toString().trim();
        word2 = editTextEnterStringAnagramWord2.getText().toString().trim();

        int len_word1 = word1.length();
        int len_word2 = word2.length();

        if (len_word1 != len_word2) {
            editTextEnterStringAnagramWord1.getText().clear();
            editTextEnterStringAnagramWord2.getText().clear();
            textViewResultAnagram.setText("");
            textViewResultAnagram.setTextColor(getColor(R.color.colorBlack));
            Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.retry),Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.getBackground().setColorFilter(getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_IN);
            toast.show();
        }

        if (len_word1 == len_word2){

            int len = len_word1;

            for (int i = 0; i < len; i++) {
                found = 0;

                for (int j = 0; j < len; j++) {

                    if (word1.charAt(i) == word2.charAt(j)) {
                        found = 1;
                        break;
                    }
                }

                if (found == 0) {
                    not_found = 1;
                    break;
                }
            }

            if (not_found == 1) {

                textViewResultAnagram.setText("They are not Anagrams");
                textViewResultAnagram.setTextColor(getColor(R.color.colorRed));

            } else {
                textViewResultAnagram.setText("They are Anagrams");
                textViewResultAnagram.setTextColor(getColor(R.color.colorGreen));
            }
        }
    }




    private void initViews(){
        editTextEnterStringAnagramWord1 = findViewById(R.id.editTextEnterString_Anagram_word1);
        editTextEnterStringAnagramWord2 = findViewById(R.id.editTextEnterString_Anagram_word2);
        btnCalculateAnagram = findViewById(R.id.btn_calculateAnagram);
        btnResetAnagram = findViewById(R.id.btnResetAnagram);
        textViewResultAnagram = findViewById(R.id.textView_result_Anagram);

        editTextWordEnteredAnagramGame = findViewById(R.id.editText_WordEntered_AnagramGame);
        textViewWordAnagramGame = findViewById(R.id.textView_Word_AnagramGame);
        btnValidateAnagramGame = findViewById(R.id.btnValidate_AnagramGame);
        btnNewGameAnagramGame = findViewById(R.id.btnNewGame_AnagramGame);
        btnGetCode = findViewById(R.id.btnGetCode);
    }

    private void validate(){
        String userWord = editTextWordEnteredAnagramGame.getText().toString().trim();

        if (wordToFind.equalsIgnoreCase(userWord)) {
            editTextWordEnteredAnagramGame.setTextColor(getColor(R.color.colorGreen));

            //CUSTOM TOAST
            /*Context context = getApplicationContext();
            LayoutInflater inflater = getLayoutInflater();
            View toastRoot = inflater.inflate(R.layout.toast_layout_anagramgame, null);
            Toast toast = new Toast(context);
            toast.setView(toastRoot);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                    0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
             */

            Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.congrats_anagram),Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_IN);
            toast.show();
            newGame();
        } else {
            Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.retry),Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.getBackground().setColorFilter(getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_IN);
            toast.show();
            editTextWordEnteredAnagramGame.setTextColor(getColor(R.color.colorRed));
        }
    }

    private void newGame(){
        wordToFind = AnagramGame.randomWord();
        String wordShuffled = AnagramGame.shuffleWord(wordToFind);
        textViewWordAnagramGame.setVisibility(View.VISIBLE);
        textViewWordAnagramGame.setText(wordShuffled);
        editTextWordEnteredAnagramGame.getText().clear();
        editTextWordEnteredAnagramGame.setTextColor(getColor(R.color.colorBlack));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }


}
