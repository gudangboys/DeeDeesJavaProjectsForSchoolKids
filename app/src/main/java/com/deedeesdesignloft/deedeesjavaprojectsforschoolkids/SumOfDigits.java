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

public class SumOfDigits extends AppCompatActivity {
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

        if (intent.hasExtra("sumOfDigits")){
            textViewLabel.setText("Get the sum of the input number");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(SumOfDigits.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    sumOfDigits();
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

    private void sumOfDigits(){
        int userNumberSumOfDigits = Integer.parseInt(editTextUserInput.getText().toString());
        int result = 0;
        int totalSum = 0;


        while (userNumberSumOfDigits > 0) {
            result = userNumberSumOfDigits % 10;
            totalSum = totalSum + result;
            userNumberSumOfDigits = userNumberSumOfDigits/10;

        }

        textViewResult.setText("Sum of the digits of your number is " + totalSum);
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
