package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

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

public class Abbreviate extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeAbbreviate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewResult.setMovementMethod(new ScrollingMovementMethod());



        Intent intent = getIntent();

        if (intent.hasExtra("abbreviate")){
            textViewLabel.setText("Abbreviate your Input to the first character of each Word/Number");
        } else {textViewLabel.setText("Error: 404");}


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResult.setVisibility(View.VISIBLE);
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(Abbreviate.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    getAbbreviation();
                    btnCalculate.setEnabled(false);
                    textViewResult.setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserInput.getText().clear();
                textViewResult.setText("");
                textViewResult.setVisibility(View.INVISIBLE);
                btnCalculate.setEnabled(true);
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(Abbreviate.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeAbbreviate", codeAbbreviate);
                startActivity(intentCode);

            }
        });

    }

    private void getAbbreviation(){
        String userSentence = editTextUserInput.getText().toString();
        String word = "", word1 = "";
        userSentence = userSentence + " ";
        int length = userSentence.length();

        for (int i = 0; i < length; i++){
            char ch = userSentence.charAt(i);
            word = word + ch;

            if (ch == ' ') {

                word = word.trim();
                word1 = word.toUpperCase();
                char chh = word1.charAt(0);
                textViewResult.append(chh + ".");
            }
        }
    }

    private void initViews(){
        editTextUserInput = findViewById(R.id.editTextUserInput);
        btnCalculate = findViewById(R.id.btn_caculate);
        btnReset = findViewById(R.id.btnReset);
        textViewResult = findViewById(R.id.textView_result);
        textViewLabel = findViewById(R.id.textView_label);
        btnGetCode = findViewById(R.id.btnGetCode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
