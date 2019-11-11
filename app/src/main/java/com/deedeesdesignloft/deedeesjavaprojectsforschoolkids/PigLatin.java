package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PigLatin extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset;
    private TextView textViewLabel, textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent.hasExtra("pigLatin")){
            textViewLabel.setText("Convert your input to Pig Latin");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(PigLatin.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    getPigLatin();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserInput.getText().clear();
                textViewResult.setText("");
            }
        });
    }

    private void getPigLatin(){
        String word = "", word2 = "";
        char ch;
        String userPigLatin = editTextUserInput.getText().toString();
        userPigLatin.trim();

        for (int i = 0; i < userPigLatin.length(); i++) {

            ch = userPigLatin.charAt(i);

            if (userPigLatin.charAt(i) == 'a' || userPigLatin.charAt(i) == 'e' ||
                    userPigLatin.charAt(i) == 'i' || userPigLatin.charAt(i) == 'o' ||
                    userPigLatin.charAt(i) == 'u') {
                for (int j = 1; j < userPigLatin.length(); j++) {
                    word += userPigLatin.charAt(j);
                }

                break;
            }

            if (userPigLatin.charAt(i) != 'a' || userPigLatin.charAt(i) != 'e' ||
                    userPigLatin.charAt(i) != 'i' || userPigLatin.charAt(i) != 'o' ||
                    userPigLatin.charAt(i) != 'u') {
                word2 += ch;
            }


        }

        textViewResult.setText(word + word2 + "ay");
    }


    private void initViews(){
        editTextUserInput = findViewById(R.id.editTextUserInput);
        btnCalculate = findViewById(R.id.btn_caculate);
        btnReset = findViewById(R.id.btnReset);
        textViewResult = findViewById(R.id.textView_result);
        textViewLabel = findViewById(R.id.textView_label);
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
