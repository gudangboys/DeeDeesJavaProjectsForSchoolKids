package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class FindFactorialOfNumber extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeFindFactorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.main_actionbar, null));

        textViewResult.setMovementMethod(new ScrollingMovementMethod());
        editTextUserInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        Intent intent = getIntent();

        if (intent.hasExtra("findFactorial")){
            textViewLabel.setText("Find the Factorial of a Number");
        } else {textViewLabel.setText("Error: 404");}

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()){
                    editTextUserInput.requestFocus();
                    Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.input_something),Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                } else {
                    findFactorial();
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
                textViewResult.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(FindFactorialOfNumber.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeFindFactorial", codeFindFactorial);
                startActivity(intentCode);

            }
        });

    }



    private void findFactorial(){
        int userNumberFindFactorial = Integer.valueOf(editTextUserInput.getText().toString());
        int factorial = 1;
        textViewResult.setText("The Factorial of " + userNumberFindFactorial + " is:" + "\n");
        textViewResult.setTextColor(getResources().getColor(R.color.colorGreen));

        for (int i = 1; i <= userNumberFindFactorial; i++){
            factorial = factorial * i;
        }
        textViewResult.append("\n" + factorial);


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
