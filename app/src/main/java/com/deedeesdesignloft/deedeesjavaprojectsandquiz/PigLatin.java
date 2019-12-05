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

public class PigLatin extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codePigLatin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.project_actionbar, null));

        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        if (intent.hasExtra("pigLatin")){
            textViewLabel.setText("Convert your input to Pig Latin");
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
                    getPigLatin();
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
                Intent intentCode = new Intent(PigLatin.this, ActivityCodeCommon.class);
                intentCode.putExtra("codePigLatin", codePigLatin);
                startActivity(intentCode);
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

        textViewResult.setText("Your text in Pig Latin:" + "\n" + "\n" + word + word2 + "ay");
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
