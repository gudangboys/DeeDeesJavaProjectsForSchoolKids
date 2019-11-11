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

public class VowelConsonantsSpaces extends AppCompatActivity {
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

        if (intent.hasExtra("vowelConsonantSpaces")){
            textViewLabel.setText("Get the total Vowels, Consonants, Spaces and Special Characters in your input");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(VowelConsonantsSpaces.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    getVowelConsonants();
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

    private void getVowelConsonants(){
        String userVowelConsonant = editTextUserInput.getText().toString();
        int vowels = 0, consonants = 0, numbers = 0, spaces = 0, specialChar = 0;

        for (int i = 0; i < userVowelConsonant.length(); i ++){

            if (userVowelConsonant.charAt(i) == 'a' || userVowelConsonant.charAt(i) == 'e' ||
                    userVowelConsonant.charAt(i) == 'i' || userVowelConsonant.charAt(i) == 'o' ||
                    userVowelConsonant.charAt(i) == 'u') {
                vowels++;
            } else if (userVowelConsonant.charAt(i) == ' ') {
                spaces++;
            } else if (userVowelConsonant.charAt(i) >= 'a' && userVowelConsonant.charAt(i) <= 'z'){
                consonants++;
            } else if(userVowelConsonant.charAt(i) >= '0' && userVowelConsonant.charAt(i) <= '9') {
                numbers++;
            } else {
                specialChar++;
            }
        }

        textViewResult.setText("Your String has: " + "\n" + vowels + " vowels"
                + "\n" + consonants + " consonants" + "\n" + numbers + " numbers" + "\n" + spaces +
                " spaces" + "\n" + specialChar + " special characters.");
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
