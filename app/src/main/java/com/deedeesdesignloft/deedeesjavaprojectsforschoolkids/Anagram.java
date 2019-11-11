package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Anagram extends AppCompatActivity {
    private EditText editTextEnterStringAnagramWord1, editTextEnterStringAnagramWord2,
            editTextWordEnteredAnagramGame;
    private Button btnCalculateAnagram, btnResetAnagram, btnValidateAnagramGame, btnNewGameAnagramGame;
    private TextView textViewResultAnagram, textViewWordAnagramGame;
    private String wordToFind;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCalculateAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEnterStringAnagramWord1.getText().toString().isEmpty() ||
                        editTextEnterStringAnagramWord2.getText().toString().isEmpty()) {
                    Toast.makeText(Anagram.this, "Enter Words First", Toast.LENGTH_SHORT).show();
                } else {
                    getAnagram();
                }
            }
        });

        btnResetAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEnterStringAnagramWord1.getText().clear();
                editTextEnterStringAnagramWord2.getText().clear();
                textViewResultAnagram.setText("");
            }
        });

        btnValidateAnagramGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextWordEnteredAnagramGame.getText().toString().isEmpty()) {
                    Toast.makeText(Anagram.this, "Enter your Solution First", Toast.LENGTH_SHORT).show();
                } else {
                    validate();
                }
            }
        });

        btnNewGameAnagramGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });


    }

    private void getAnagram(){
        String word1 = "", word2 = "";
        int count = 0, no_count = 0;

        word1 = editTextEnterStringAnagramWord1.getText().toString();
        word2 = editTextEnterStringAnagramWord2.getText().toString();

        int len_word1 = word1.length();
        int len_word2 = word2.length();

        if (len_word1 == len_word2){

            int length = len_word1;

            for (int i = 0; i < length; i++) {
                count = 0;

                for (int j = 0; j < length; j++) {

                    if (word1.charAt(i) == word2.charAt(j)) {
                        count = 1;
                        break;
                    }
                }

                if (count == 0) {
                    no_count = 1;
                    break;
                }
            }

            if (no_count == 1) {

                textViewResultAnagram.setText("They are not Anagrams");
            } else {
                textViewResultAnagram.setText("They are Anagrams");
            }
        }
    }




    private void initViews(){
        editTextEnterStringAnagramWord1 = findViewById(R.id.editTextEnterString_Anagram_word1);
        editTextEnterStringAnagramWord2 = findViewById(R.id.editTextEnterString_Anagram_word2);
        btnCalculateAnagram = findViewById(R.id.btn_caculateAnagram);
        btnResetAnagram = findViewById(R.id.btnResetAnagram);
        textViewResultAnagram = findViewById(R.id.textView_result_Anagram);

        editTextWordEnteredAnagramGame = findViewById(R.id.editText_WordEntered_AnagramGame);
        textViewWordAnagramGame = findViewById(R.id.textView_Word_AnagramGame);
        btnValidateAnagramGame = findViewById(R.id.btnValidate_AnagramGame);
        btnNewGameAnagramGame = findViewById(R.id.btnNewGame_AnagramGame);
    }

    private void validate(){
        String userWord = editTextWordEnteredAnagramGame.getText().toString();

        if (wordToFind.equals(userWord)) {
            Toast.makeText(this, "Congratulations! You found the Word! " + wordToFind, Toast.LENGTH_SHORT).show();
            newGame();
        } else {
            Toast.makeText(this, "Retry!", Toast.LENGTH_SHORT).show();
        }
    }

    private void newGame(){
        wordToFind = AnagramGame.randomWord();
        String wordShuffled = AnagramGame.shuffleWord(wordToFind);
        textViewWordAnagramGame.setText(wordShuffled);
        editTextWordEnteredAnagramGame.getText().clear();
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
