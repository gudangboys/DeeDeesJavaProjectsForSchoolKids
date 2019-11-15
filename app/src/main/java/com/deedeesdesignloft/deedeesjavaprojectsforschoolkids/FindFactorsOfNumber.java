package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FindFactorsOfNumber extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeFindFactors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewResult.setMovementMethod(new ScrollingMovementMethod());
        editTextUserInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        Intent intent = getIntent();

        if (intent.hasExtra("findFactors")){
            textViewLabel.setText("Find the Factors of a Number");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(FindFactorsOfNumber.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    findFactors();
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
                Intent intentCode = new Intent(FindFactorsOfNumber.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeFindFactors", codeFindFactors);
                startActivity(intentCode);

            }
        });


    }



    private void findFactors(){
        List<Integer> factors = new ArrayList<>();
        int userNumberFindFactor = Integer.valueOf(editTextUserInput.getText().toString());
        textViewResult.setText("The Factors of " + userNumberFindFactor + " are:" + "\n" + "\n");

        for (int i = 1; i <= userNumberFindFactor; i++) {

            if (userNumberFindFactor%i == 0) {
                factors.add(i);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int factorsResult : factors) {
            builder.append(factorsResult + "\n");
        }

        textViewResult.append(builder.toString());


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
