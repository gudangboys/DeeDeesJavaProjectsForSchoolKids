package com.deedeesdesignloft.deedeesjavaprojectsandquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Tables extends AppCompatActivity {
    private EditText editTextEnterNumberTables, editTextMultiplyUpto;
    private Button btnListTables, btnResetTables, btnGetCode;
    private TextView textViewResultTables;
    private String codeTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.project_actionbar, null));

        btnListTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEnterNumberTables.getText().toString().isEmpty() ||
                editTextMultiplyUpto.getText().toString().isEmpty()) {
                    editTextEnterNumberTables.requestFocus();
                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.input_something), Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    TextView toastMessage = view.findViewById(android.R.id.message);
                    toastMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYellow));
                    view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toast_shape_black));
                    toast.show();
                } else {
                    textViewResultTables.setVisibility(View.VISIBLE);
                    showTables();
                    //btnListTables.setEnabled(false);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnResetTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEnterNumberTables.getText().clear();
                editTextMultiplyUpto.getText().clear();
                textViewResultTables.setText("");
                textViewResultTables.setVisibility(View.INVISIBLE);
                //btnListTables.setEnabled(true);
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(Tables.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeTables", codeTables);
                startActivity(intentCode);
            }
        });

    }

    private void showTables(){
        int userNumberTables = Integer.parseInt(editTextEnterNumberTables.getText().toString());
        int userMultiplyUpto = Integer.parseInt(editTextMultiplyUpto.getText().toString());
        textViewResultTables.setText("Here are the tables for " + userNumberTables + " for you multiplied " +
                 userMultiplyUpto + " times" + "\n");


        //List<Integer> tableResult = new ArrayList<>();
        //List<String> setTextResult = new ArrayList<>();

                String tableResult;

        for (int i = 1; i <= userMultiplyUpto; i++) {
            tableResult = (userNumberTables + " x " + i + " = " + i * userNumberTables);
            textViewResultTables.append("\n" + tableResult);
            textViewResultTables.setMovementMethod(new ScrollingMovementMethod());

        }

    }

    private void initViews(){
        editTextEnterNumberTables = findViewById(R.id.editTextEnterNumber_Tables);
        editTextMultiplyUpto = findViewById(R.id.editTextEnterNumber_MultiplyUpto);
        btnListTables = findViewById(R.id.btnListTables);
        btnResetTables = findViewById(R.id.btnResetTables);
        textViewResultTables = findViewById(R.id.textView_result_Tables);
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
