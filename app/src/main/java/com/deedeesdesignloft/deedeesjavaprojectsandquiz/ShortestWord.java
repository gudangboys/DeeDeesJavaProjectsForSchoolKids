package com.deedeesdesignloft.deedeesjavaprojectsandquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ShortestWord extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeShortestWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.project_actionbar, null));

        Intent intent = getIntent();

        if (intent.hasExtra("shortestWord")){
            textViewLabel.setText("Find the Smallest word");
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
                    getShortestWord();
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
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(ShortestWord.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeShortestWord", codeShortestWord);
                startActivity(intentCode);
            }
        });


    }

    private void getShortestWord(){

        String userInput = editTextUserInput.getText().toString();
        String[] words = userInput.split(" ");
        int min;
        int minWord = 0;

        min = (words[0].length());

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() < min) {
                min = words[i].length();
                minWord = i;
            }
        }

        textViewResult.setText("\"" + words[minWord] + "\"" + " is the shortest word.");

        /*
        String userInput = editTextUserInput.getText().toString();
        String word = "", min = "";
        int p = 0;
        userInput = userInput + " ";
        int l = userInput.length();
        char ch;

        for (int i = 0; i < l; i++) {
            ch = userInput.charAt(i);
            word = word + ch;

            if (ch==' ') {
                int len = word.length();

                if (p < len) {
                    if (min.length() > len)
                        p = len; min = word;
                        word = " ";
                }

            }
        }

         */

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
