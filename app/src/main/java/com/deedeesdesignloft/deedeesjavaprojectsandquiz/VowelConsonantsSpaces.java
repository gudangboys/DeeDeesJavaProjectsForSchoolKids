package com.deedeesdesignloft.deedeesjavaprojectsandquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class VowelConsonantsSpaces extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeVowelsConsonants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.project_actionbar, null));
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        if (intent.hasExtra("vowelConsonantSpaces")){
            textViewLabel.setText("Get the total Vowels, Consonants, Spaces and Special Characters in your input");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.input_something), Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    TextView toastMessage = view.findViewById(android.R.id.message);
                    toastMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYellow));
                    view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toast_shape_black));
                    toast.show();
                } else {
                    getVowelConsonants();
                    //btnCalculate.setEnabled(false);
                    textViewResult.setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserInput.getText().clear();
                textViewResult.setText("");
                textViewResult.setVisibility(View.INVISIBLE);
                //btnCalculate.setEnabled(true);
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(VowelConsonantsSpaces.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeVowelsConsonants", codeVowelsConsonants);
                startActivity(intentCode);
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

        textViewResult.setText("Your String has: " + "\n" + "\n" + vowels + " vowels"
                + "\n" + consonants + " consonants" + "\n" + numbers + " numbers" + "\n" + spaces +
                " spaces" + "\n" + specialChar + " special characters.");
    }

    private void initViews(){
        editTextUserInput = findViewById(R.id.editTextUserInput);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnReset = findViewById(R.id.btnReset);
        textViewResult = findViewById(R.id.textView_result);
        textViewLabel = findViewById(R.id.textView_label);
        btnGetCode = findViewById(R.id.btnGetCode);
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
