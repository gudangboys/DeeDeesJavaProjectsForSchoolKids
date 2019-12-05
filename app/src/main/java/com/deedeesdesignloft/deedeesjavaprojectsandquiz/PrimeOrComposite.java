package com.deedeesdesignloft.deedeesjavaprojectsandquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class PrimeOrComposite extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codePrimeOrComposite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.project_actionbar, null));

        editTextUserInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        Intent intent = getIntent();

        if (intent.hasExtra("primeOrComposite")){
            textViewLabel.setText("Check if your number is Prime or Composite");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResult.setVisibility(View.VISIBLE);
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.input_something), Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    TextView toastMessage = view.findViewById(android.R.id.message);
                    toastMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYellow));
                    view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toast_shape_black));
                    toast.show();
                } else {
                    primeOrComposite();
                    //btnCalculate.setEnabled(false);
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
                Intent intentCode = new Intent(PrimeOrComposite.this, ActivityCodeCommon.class);
                intentCode.putExtra("codePrimeOrComposite", codePrimeOrComposite);
                startActivity(intentCode);
            }
        });

    }

    private void primeOrComposite(){
        int count = 0;
        int userInputPrimeOrcomposite = Integer.parseInt(editTextUserInput.getText().toString());

        for (int i = 1; i <= userInputPrimeOrcomposite; i++) {
            if (userInputPrimeOrcomposite%i==0) {
                count++;
            }
        }

        if (count > 2) {
            textViewResult.setText(userInputPrimeOrcomposite + " is a Composite Number");
        } else {
            textViewResult.setText(userInputPrimeOrcomposite + " is a Prime Number");
        }

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
