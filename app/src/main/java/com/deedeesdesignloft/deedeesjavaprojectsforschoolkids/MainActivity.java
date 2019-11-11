package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnFindFactorialOfNumber, btnFindfactorsOfNumber, btnTables,
            btnPrimeOrComposite, btnPerfectNumber, btnSumOfDigits, btnPalindrome, btnAutomorphic,
            btnNiven, btnVowelConsonants, btnEachWordNewLine, btnPigLatin, btnAnagram, btnAbbreviate,
            btnLongestWord, btnShortestWord, btnCitySTDCode;

    String abbreviate, automorphic, eachWordNewLine, findFactorial, findFactors, niven, palindrome,
            perfectNumber, pigLatin, primeOrComposite, sumOfDigits, vowelConsonantSpaces, longestWord,
            shortestWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnFindFactorialOfNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindFactorialOfNumber.class);
                intent.putExtra("findFactorial", findFactorial);
                startActivity(intent);
            }


        });

        btnFindfactorsOfNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindFactorsOfNumber.class);
                intent.putExtra("findFactors", findFactors);
                startActivity(intent);
            }
        });

        btnTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Tables.class);
                startActivity(intent);
            }
        });

        btnPrimeOrComposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrimeOrComposite.class);
                intent.putExtra("primeOrComposite", primeOrComposite);
                startActivity(intent);
            }
        });

        btnPerfectNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PerfectNumber.class);
                intent.putExtra("perfectNumber", perfectNumber);
                startActivity(intent);
            }
        });

        btnSumOfDigits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SumOfDigits.class);
                intent.putExtra("sumOfDigits", sumOfDigits);
                startActivity(intent);
            }
        });

        btnPalindrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Palindrome.class);
                intent.putExtra("palindrome", palindrome);
                startActivity(intent);
            }
        });

        btnAutomorphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Automorphic.class);
                intent.putExtra("automorphic", automorphic);
                startActivity(intent);
            }
        });

        btnNiven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Niven.class);
                intent.putExtra("niven", niven);
                startActivity(intent);
            }
        });

        btnVowelConsonants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VowelConsonantsSpaces.class);
                intent.putExtra("vowelConsonantSpaces",vowelConsonantSpaces);
                startActivity(intent);
            }
        });

        btnEachWordNewLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EachWordNewLine.class);
                intent.putExtra("eachWordNewLine", eachWordNewLine);
                startActivity(intent);
            }
        });

        btnPigLatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PigLatin.class);
                intent.putExtra("pigLatin", pigLatin);
                startActivity(intent);
            }
        });

        btnAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Anagram.class);
                startActivity(intent);
            }
        });

        btnAbbreviate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Abbreviate.class);
                intent.putExtra("abbreviate", abbreviate);
                startActivity(intent);
            }
        });

        btnLongestWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LongestWord.class);
                intent.putExtra("longestWord", longestWord);
                startActivity(intent);
            }
        });

        btnShortestWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShortestWord.class);
                intent.putExtra("shortestWord", shortestWord);
                startActivity(intent);
            }
        });

        btnCitySTDCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CitySTDCode.class);
                startActivity(intent);
            }
        });

    }

    private void initViews(){
        btnFindFactorialOfNumber = findViewById(R.id.btn_Factorial);
        btnFindfactorsOfNumber = findViewById(R.id.btn_Factors);
        btnTables = findViewById(R.id.btn_Tables);
        btnPrimeOrComposite = findViewById(R.id.btn_PrimeOrComposite);
        btnPerfectNumber = findViewById(R.id.btn_PerfectNumber);
        btnSumOfDigits = findViewById(R.id.btn_SumOfDigits);
        btnPalindrome = findViewById(R.id.btn_Palindrome);
        btnAutomorphic = findViewById(R.id.btn_Automorphic);
        btnNiven = findViewById(R.id.btn_Niven);
        btnVowelConsonants = findViewById(R.id.btn_VowelConsonant);
        btnEachWordNewLine = findViewById(R.id.btn_EachWordNewLine);
        btnPigLatin = findViewById(R.id.btn_PigLatin);
        btnAnagram = findViewById(R.id.btn_Anagram);
        btnAbbreviate = findViewById(R.id.btn_Abbreviate);
        btnLongestWord = findViewById(R.id.btn_LongestWord);
        btnShortestWord = findViewById(R.id.btn_ShortestWord);
        btnCitySTDCode = findViewById(R.id.btn_CitySTDCode);
    }

}
