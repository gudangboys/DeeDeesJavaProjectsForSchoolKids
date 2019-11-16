package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CitySTDCode extends AppCompatActivity {
    private EditText editTextUserAddCity, editTextUserAddSTD, editTextSearchCity;
    private Button btnAddData, btnSearchCity, btnResetCitySTD, btnListAllCities, btnResetListCity,
    btnGetCode;
    private TextView textViewResultCitySTD, textViewCityListAll;
    private String codeCitySTD;
    ArrayList<String> city = new ArrayList<>();
    ArrayList<String> stdCode = new ArrayList<>();
    //String city[] = new String[n];
    //int stdCode[] = new int[n];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_stdcode);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserAddCity.getText().toString().isEmpty() ||
                        editTextUserAddSTD.getText().toString().isEmpty()){
                    Toast.makeText(CitySTDCode.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                    editTextUserAddCity.requestFocus();

                } else {
                    addCity();
                    editTextUserAddCity.getText().clear();
                    editTextUserAddSTD.getText().clear();
                    editTextUserAddCity.requestFocus();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnSearchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCity();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
                textViewResultCitySTD.setTextColor(getResources().getColor(R.color.colorBlack));
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

    private void addCity(){
        city.add(editTextUserAddCity.getText().toString());
        stdCode.add(editTextUserAddSTD.getText().toString());

        //int n = 0;
        //String userAddCity = editTextUserAddCity.getText().toString();
        //int userAddSTD = Integer.parseInt(editTextUserAddSTD.getText().toString());

        //for (int i = 0; i < n; i++){
        //    city[i] = userAddCity;
        //    stdCode[i] = userAddSTD;
        //}
    }


    private void searchCity(){
        String userSearchCity = editTextSearchCity.getText().toString();

        if (userSearchCity.isEmpty()) {

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

            for (int j = 0; j < city.size(); j++) {
                if (userSearchCity.equalsIgnoreCase(city.get(j))) {
                    textViewResultCitySTD.setVisibility(View.VISIBLE);
                    textViewResultCitySTD.setText("City: " + userSearchCity + "\n" + "STD: " + stdCode.get(j));
                    textViewResultCitySTD.setTextColor(getResources().getColor(R.color.colorGreen));
                } else {
                    textViewResultCitySTD.setVisibility(View.VISIBLE);
                    textViewResultCitySTD.setText("City not found!");
                    textViewResultCitySTD.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }
        }

    }

    private void listAllCity(){
        StringBuilder builder = new StringBuilder();
        for (String cities : city) {
            builder.append(cities + "\n");
        }

        textViewCityListAll.setText(builder.toString());
    }





    private void initViews(){
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
