package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PerfectNumber extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codePerfectNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.main_actionbar));

        editTextUserInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        Intent intent = getIntent();

        if (intent.hasExtra("perfectNumber")){
            textViewLabel.setText("Check if your number is a Perfect Number");
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
                    perfectNumber();
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
                textViewResult.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(PerfectNumber.this, ActivityCodeCommon.class);
                intentCode.putExtra("codePerfectNumber", codePerfectNumber);
                startActivity(intentCode);
            }
        });

    }

    private void perfectNumber(){
        int perfectNumberSum = 0;
        int userPerfectNumber = Integer.parseInt(editTextUserInput.getText().toString());

        for (int i = 1; i < userPerfectNumber; i++){
            if (userPerfectNumber % i == 0){
                perfectNumberSum = perfectNumberSum + i;
            }
        }

        if (perfectNumberSum == userPerfectNumber){
            textViewResult.setText(userPerfectNumber + " is a Perfect Number");
            textViewResult.setTextColor(getResources().getColor(R.color.colorGreen));
        } else {
            textViewResult.setText(userPerfectNumber + " is not a Perfect Number");
            textViewResult.setTextColor(getResources().getColor(R.color.colorRed));
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
