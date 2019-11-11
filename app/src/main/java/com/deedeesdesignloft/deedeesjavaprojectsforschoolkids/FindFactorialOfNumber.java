package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindFactorialOfNumber extends AppCompatActivity {
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

        if (intent.hasExtra("findFactorial")){
            textViewLabel.setText("Find the Factorial of a Number");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResult.setVisibility(View.VISIBLE);
                if (editTextUserInput.getText().toString().isEmpty()){
                    editTextUserInput.requestFocus();
                    Toast.makeText(FindFactorialOfNumber.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    findFactorial();
                    btnCalculate.setEnabled(false);
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


    }





    private void findFactorial(){
        int userNumberFindFactorial = Integer.valueOf(editTextUserInput.getText().toString());
        int factorial = 1;
        textViewResult.setText("The Factorials of " + userNumberFindFactorial + " are:" + "\n");
        //textViewResult.setGravity(Gravity.CENTER);



        for (int i = 1; i <= userNumberFindFactorial; i++){
            factorial = factorial * i;
            textViewResult.append("\n" + factorial);

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
