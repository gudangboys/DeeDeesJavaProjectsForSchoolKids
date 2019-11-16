package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EachWordNewLine extends AppCompatActivity {
    private EditText editTextUserInput;
    private Button btnCalculate, btnReset, btnGetCode;
    private TextView textViewLabel, textViewResult;
    private String codeEachWordNewLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_common);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        if (intent.hasExtra("eachWordNewLine")){
            textViewLabel.setText("Print Every Word in a New Line");
        } else {textViewLabel.setText("Error: 404");}


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    Context context = getApplicationContext();
                    LayoutInflater inflater = getLayoutInflater();
                    View toastRoot = inflater.inflate(R.layout.toast_layout_blue, null);
                    Toast toast = new Toast(context);
                    toast.setView(toastRoot);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                            0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    getEachWordNewLine();
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
                Intent intentCode = new Intent(EachWordNewLine.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeEachWordNewLine", codeEachWordNewLine);
                startActivity(intentCode);

            }
        });


    }

    private void getEachWordNewLine(){
        String userEachWordNewLine = editTextUserInput.getText().toString();

        if (!userEachWordNewLine.contains(" ")) {
            textViewResult.setText("Your input is not a Sentence!");
            textViewResult.setTextColor(getResources().getColor(R.color.colorRed));
        } else {

            String sentence = userEachWordNewLine.replace(" ", System.lineSeparator());
            textViewResult.setText("Printed every word in a new line for you:" + "\n" + "\n" + sentence);
            textViewResult.setTextColor(getResources().getColor(R.color.colorGreen));
        }



        //OR

        //String sentence2 = userEachWordNewLine;
        //for(String word : userEachWordNewLine.split(" ")){
        //    textViewResultEachWordNewLine(word);
        //}


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
