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

import java.util.ArrayList;
import java.util.Objects;

public class CitySTDCode extends AppCompatActivity {
    private EditText editTextUserAddCity, editTextUserAddSTD, editTextSearchCity;
    private Button btnAddData, btnSearchCity, btnResetCitySTD, btnListAllCities, btnResetListCity,
            btnGetCode;
    private TextView textViewResultCitySTD, textViewCityListAll;
    private String codeCitySTD;
    ArrayList<String> city = new ArrayList<>();
    ArrayList<Integer> stdCode = new ArrayList<>();
    //String city[] = new String[n];
    //int stdCode[] = new int[n];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_stdcode);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.project_actionbar, null));

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserAddCity.getText().toString().isEmpty() ||
                        editTextUserAddSTD.getText().toString().isEmpty()) {

                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.input_something), Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    TextView toastMessage = view.findViewById(android.R.id.message);
                    toastMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYellow));
                    view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toast_shape_black));
                    toast.show();

                } else {
                    addCity();
                    editTextUserAddCity.getText().clear();
                    editTextUserAddSTD.getText().clear();
                    editTextUserAddCity.requestFocus();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnSearchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCity();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        btnResetCitySTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearchCity.getText().clear();
                textViewResultCitySTD.setText("");

            }
        });

        btnListAllCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllCity();
                textViewCityListAll.setVisibility(View.VISIBLE);
            }
        });

        btnResetListCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewCityListAll.setText("");
                textViewResultCitySTD.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCode = new Intent(CitySTDCode.this, ActivityCodeCommon.class);
                intentCode.putExtra("codeCitySTD", codeCitySTD);
                startActivity(intentCode);

            }
        });

    }

    private void addCity() {
        String userInputCity = editTextUserAddCity.getText().toString().trim();
        int userInputSTD = Integer.parseInt(editTextUserAddSTD.getText().toString().trim());

        /*for (int j = 0; j < city.size(); j++) {
            if (userInputCity.equalsIgnoreCase(city.get(j))) {
                textViewResultCitySTD.setVisibility(View.VISIBLE);
                textViewResultCitySTD.setText(getResources().getString(R.string.data_already_exists));
                textViewResultCitySTD.setTextColor(getResources().getColor(R.color.colorGreen));
            } else {

         */
        city.add(userInputCity);
        stdCode.add(userInputSTD);

    }


    //int n = 0;
    //String userAddCity = editTextUserAddCity.getText().toString();
    //int userAddSTD = Integer.parseInt(editTextUserAddSTD.getText().toString());

    //for (int i = 0; i < n; i++){
    //    city[i] = userAddCity;
    //    stdCode[i] = userAddSTD;
    //}


    private void searchCity() {
        String userSearchCity = editTextSearchCity.getText().toString().trim();
        if (userSearchCity.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.input_something), Toast.LENGTH_SHORT);
            View view = toast.getView();
            TextView toastMessage = view.findViewById(android.R.id.message);
            toastMessage.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYellow));
            view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toast_shape_black));
            toast.show();
        } else {
            for (int j = 0; j < city.size(); j++) {
                if (userSearchCity.equalsIgnoreCase(city.get(j))) {
                    textViewResultCitySTD.setVisibility(View.VISIBLE);
                    textViewResultCitySTD.setText("City: " + userSearchCity + "\n" + "STD: " + stdCode.get(j));
                    textViewResultCitySTD.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGreen));
                } else {
                    textViewResultCitySTD.setVisibility(View.VISIBLE);
                    textViewResultCitySTD.setText("City not found!");
                    textViewResultCitySTD.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorRed));
                }
            }
        }

    }

    private void listAllCity() {

        /*

        if (city == null){
            Toast toast =  Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_data_first),Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.getBackground().setColorFilter(getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_IN);
            toast.show();

        } else {

         */
        StringBuilder builder = new StringBuilder();
        for (String cities : city) {
            builder.append(cities).append("\n");
        }

        textViewCityListAll.setMovementMethod(new ScrollingMovementMethod());
        textViewCityListAll.setText(builder.toString());


    }


    private void initViews() {
        editTextUserAddCity = findViewById(R.id.editTextUserAddCitySTDCode);
        editTextUserAddSTD = findViewById(R.id.editTextUserAddSTD_CitySTDCode);
        editTextSearchCity = findViewById(R.id.editTextSearchCity);
        btnAddData = findViewById(R.id.btnAddData);
        btnSearchCity = findViewById(R.id.btnSearchCity);
        btnResetCitySTD = findViewById(R.id.btnResetSTDCityCode);
        textViewResultCitySTD = findViewById(R.id.textView_result_CitySTDCode);
        textViewCityListAll = findViewById(R.id.textView_CitySTD_ListAll);
        btnListAllCities = findViewById(R.id.btnListCity);
        btnResetListCity = findViewById(R.id.btnResetListSTDCityCode);
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
