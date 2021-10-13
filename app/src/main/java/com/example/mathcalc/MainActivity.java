package com.example.mathcalc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    //views
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnZero;

    Button btnMultiply;
    Button btnDivide;
    Button btnPlus;
    Button btnMinus;

    Button btnEquals;
    Button btnClear;
    Button btnFlip;
    Button btnDecimal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup views
        //nums
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(onButtonClicked);

        btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(onButtonClicked);

        btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener(onButtonClicked);

        btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener(onButtonClicked);

        btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener(onButtonClicked);

        btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener(onButtonClicked);

        btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(onButtonClicked);

        btnEight = findViewById(R.id.btnEight);
        btnEight.setOnClickListener(onButtonClicked);

        btnNine = findViewById(R.id.btntNine);
        btnNine.setOnClickListener(onButtonClicked);

        btnZero = findViewById(R.id.btnZero);
        btnZero.setOnClickListener(onButtonClicked);


        //ops

        btnMultiply = findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(onButtonClicked);

        btnDivide = findViewById(R.id.btnDivision);
        btnDivide.setOnClickListener(onButtonClicked);

        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(onButtonClicked);

        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(onButtonClicked);

        btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(onButtonClicked);


        //etc

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(onButtonClicked);

        btnFlip = findViewById(R.id.btnFlip);
        btnFlip.setOnClickListener(onButtonClicked);

        btnDecimal = findViewById(R.id.btnDecimal);
        btnDecimal.setOnClickListener(onButtonClicked);
    }


    public View.OnClickListener onButtonClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnOne:
                    Toast.makeText(getApplicationContext(), "but1", Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnTwo:
                    Toast.makeText(getApplicationContext(), "but2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnThree:
                    Toast.makeText(getApplicationContext(), "but3", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "other", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
}