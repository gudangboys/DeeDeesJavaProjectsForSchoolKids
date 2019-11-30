package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
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

import java.util.Objects;

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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.main_actionbar, null));

        textViewResult.setMovementMethod(new ScrollingMovementMethod());



        Intent intent = getIntent();

        if (intent.hasExtra("abbreviate")){
            textViewLabel.setText("Abbreviate your Input to the first character of each Word/Number");
        } else {textViewLabel.setText("Error: 404");}


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserInput.getText().toString().isEmpty()) {
                    //CUSTOM TOAST
                    //Context context = getApplicationContext();
                    //LayoutInflater inflater = getLayoutInflater();
                    //View toastRoot = inflater.inflate(R.layout.toast_layout_blue, null);
                    //Toast toast = new Toast(context);
                    //toast.setView(toastRoot);
                    //toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                    //        0, 0);
                    //toast.setDuration(Toast.LENGTH_SHORT);
                    //toast.show();

                    Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.input_something),Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                } else {
                    getAbbreviation();
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
                Intent intentCode = new Intent(Abbreviate.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeAbbreviate", codeAbbreviate);
                startActivity(intentCode);

            }
        });

    }

    private void getAbbreviation(){
        textViewResult.setVisibility(View.VISIBLE);
        String userSentence = editTextUserInput.getText().toString();
        String word= "", word1 = "";
        userSentence = userSentence + " ";
        int length = userSentence.length();

        for (int i = 0; i < length; i++){
            char ch = userSentence.charAt(i);
            word +=  ch;

            if (ch == ' ') {
                word = word.trim();
                word1 = word;//.toUpperCase();
                word = "";
                char chh = word1.charAt(0);
                textViewResult.append(chh + "." + "\n");
            }
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
