package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tables extends AppCompatActivity {
    private EditText editTextEnterNumberTables, editTextmultiplyUpto;
    private Button btnListTables, btnResetTables;
    private TextView textViewResultTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnListTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEnterNumberTables.getText().toString().isEmpty() ||
                editTextmultiplyUpto.getText().toString().isEmpty()) {
                    editTextEnterNumberTables.requestFocus();
                    Toast.makeText(Tables.this, "Enter Number First", Toast.LENGTH_SHORT).show();
                } else {
                    textViewResultTables.setVisibility(View.VISIBLE);
                    showTables();
                    btnListTables.setEnabled(false);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnResetTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEnterNumberTables.getText().clear();
                editTextmultiplyUpto.getText().clear();
                textViewResultTables.setText("");
                textViewResultTables.setVisibility(View.INVISIBLE);
                btnListTables.setEnabled(true);
            }
        });

    }

    private void showTables(){
        int userNumberTables = Integer.parseInt(editTextEnterNumberTables.getText().toString());
        int userMultiplyUpto = Integer.parseInt(editTextmultiplyUpto.getText().toString());
        textViewResultTables.setText("Here are the " + userNumberTables + " times tables for you" + "\n");


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
        editTextmultiplyUpto = findViewById(R.id.editTextEnterNumber_MultiplyUpto);
        btnListTables = findViewById(R.id.btnListTables);
        btnResetTables = findViewById(R.id.btnResetTables);
        textViewResultTables = findViewById(R.id.textView_result_Tables);
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
