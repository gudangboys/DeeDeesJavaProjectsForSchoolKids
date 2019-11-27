package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LongestWord extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeLongestWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.main_actionbar));

        Intent intent = getIntent();

        if (intent.hasExtra("longestWord")){
            textViewLabel.setText("Find the Longest word");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.input_something),Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                } else {
                    getLongestWord();
                    //btnCalculate.setEnabled(false);
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
                //btnCalculate.setEnabled(true);
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(LongestWord.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeLongestWord", codeLongestWord);
                startActivity(intentCode);

            }
        });
    }

    private void getLongestWord(){
        String userInput = editTextUserInput.getText().toString();
        String word = "", max = "";
        int l, p = 0;
        userInput = " " + userInput + " ";
        l = userInput.length();
        char ch;

        for (int i = 0; i < l; i++) {
            ch = userInput.charAt(i);
            word = word + ch;

            if (ch==' ') {
                int len = word.length();

                if (len < p) {
                    word = "";

                } else {
                    p = len; max = word;
                    word = " ";
                }
            }
        }

        textViewResult.setText("\"" + max + "\"" + " is the longest word.");
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

