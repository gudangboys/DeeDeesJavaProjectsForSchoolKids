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

public class Palindrome extends AppCompatActivity {
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

        if (intent.hasExtra("palindrome")){
            textViewLabel.setText("Check if your number is a Palindrome");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(Palindrome.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    getPalindrome();
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


    private void getPalindrome(){
        int rev = 0, r, ori;

        int userPalindrome = Integer.parseInt(editTextUserInput.getText().toString());

        ori = userPalindrome;

        while (userPalindrome != 0) {
            r = userPalindrome % 10;
            rev = rev * 10 + r;
            userPalindrome = userPalindrome/10;
        }

        if (ori == rev){
            textViewResult.setText(ori + " is a Plaindrome Number");
        } else {
            textViewResult.setText(ori + " is not a Plaindrome Number");
        }


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
