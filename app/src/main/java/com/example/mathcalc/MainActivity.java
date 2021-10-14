package com.example.mathcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    calculationClass calc = new calculationClass();



    //text views
    private TextView textCurrentDisplay;

    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;

    private Button btnMultiply;
    private Button btnDivide;
    private Button btnPlus;
    private Button btnMinus;

    private Button btnEquals;
    private Button btnClear;
    private Button btnBackspace;
    private Button btnFlip;
    private Button btnDecimal;

    //keeping track of things
    private String firstNum = ""; //why I didn't use strings and convert then before the calc, I don't have an answer, but I'm too far invested in the way I'm doing it now - update: it was a pain but I changed things to work with strings lol
    private String op = "";
    private String secondNum = "" ;
    private boolean opStarted = false; //checks if an initial op has been pressed
    private boolean swap = false; //buttons will track first num and trigger swap when an op is pressed, when swap is active secondNum will begin being tracked

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup views
        //text
        textCurrentDisplay = findViewById(R.id.textCurrentDisplay);

        //nums
        //button views
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

        btnNine = findViewById(R.id.btnNine);
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


        //etc
        btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(onButtonClicked);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(onButtonClicked);

        btnBackspace = findViewById(R.id.btnBackspace);
        btnBackspace.setOnClickListener(onButtonClicked);

        btnFlip = findViewById(R.id.btnFlip);
        btnFlip.setOnClickListener(onButtonClicked);

        btnDecimal = findViewById(R.id.btnDecimal);
        btnDecimal.setOnClickListener(onButtonClicked);
    }


    public View.OnClickListener onButtonClicked = new View.OnClickListener()
    {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v)
        {

            switch(v.getId())
            {
                //numbers
                case R.id.btnOne:
                    decidePlacement("1");
                    break;
                case R.id.btnTwo:
                    decidePlacement("2");
                    break;
                case R.id.btnThree:
                    decidePlacement("3");
                    break;
                case R.id.btnFour:
                    decidePlacement("4");
                    break;
                case R.id.btnFive:
                    decidePlacement("5");
                    break;
                case R.id.btnSix:
                    decidePlacement("6");
                    break;
                case R.id.btnSeven:
                    decidePlacement("7");
                    break;
                case R.id.btnEight:
                    decidePlacement("8");
                    break;
                case R.id.btnNine:
                    decidePlacement("9");
                    break;
                case R.id.btnZero:
                    decidePlacement("0");
                    break;

                    //ops
                case R.id.btnMultiply:
                    resolveOperator("*");
                    break;
                case R.id.btnDivision:
                    resolveOperator("/");
                    break;
                case R.id.btnPlus:
                    resolveOperator("+");
                    break;
                case R.id.btnMinus:
                    resolveOperator("-");
                    break;


                    //etc
                case R.id.btnDecimal:
                    decidePlacement(".");
                    break;
                case R.id.btnEquals:
                    resolveEquals();
                    break;
                case R.id.btnClear:
                    resolveClear();
                    break;
                case R.id.btnBackspace:
                    resolveBackspace();
                    break;
                case R.id.btnFlip:
                    resolveFlip();
                    break;
            }

            //after specific button stuff is resolved, update displays
            updateDisplays();

            if (firstNum.equals("NaN"))
            {
                resolveClear(); //resets completely BEFORE next input is input if there's a divide by 0 error
                //but AFTER update so the error properly displays
            }
        }
    };
    
    public void decidePlacement(String n) //decide whether string will be placed in the first or second number
    {
        if (swap && opStarted)
        {
            secondNum = createPlacement(n,secondNum);
        }
        else
        {
            firstNum = createPlacement(n, firstNum);
        }
    }

    public String createPlacement(String n, String num)
    {
        if (num.equals("0")) //if there's just a zero replace it
        {
            num = n; //for example displays 15 instead of 015
        }
        else
        {
            num = appendToString(num, n);
        }

        if (num.equals("."))
        {
            num = "0.";
        }

        return num;
    }

    public void resolveOperator(String o)
    {
        if (opStarted && !secondNum.equals("")) //if this isn't the first op pressed and secondnum has something
        {
            firstNum = calc.calculate(Double.parseDouble(firstNum), op, Double.parseDouble(secondNum)); //resolve equation and store it in firstNum
            resetStates();
            op = o; //set op after resetting
            opStarted = true; //set started after resetting
        }
        else if (!firstNum.equals(""))
        {
            op = o; //only set if number has been entered
            opStarted = true;
            swap = true;
        }
    }

    public void resolveEquals()
    {
        if (swap && !op.equals("") && !secondNum.equals("")) //if in swap state and op/secondnum isn't empty resolve equation
        {
            firstNum = calc.calculate(Double.parseDouble(firstNum), op, Double.parseDouble(secondNum)); //resolve equation and store it in firstNum
            resetStates();
        }
    }

    public void resolveClear()
    {
        //reset all trackers
        firstNum = "";
        swap = false;
        resetStates();
    }

    public void resolveBackspace()
    {
        if (swap && opStarted)
        {
            secondNum = createBackspaceString(secondNum);
        }
        else
        {
            firstNum = createBackspaceString(firstNum);
        }
    }

    public String createBackspaceString(String n)
    {
        String str;
        StringBuilder newstr = new StringBuilder();

        str = n;
        if(!str.equals("")) //if string isn't empty append all but last number to new string
        {
            for (int i = 0; i < str.length() - 1; i++)
            {
                newstr.append(str.charAt(i));
            }
            n = newstr.toString();
        }
        if (n.equals("-"))//if all that remains is -, remove it to avoid crashes
        {
            n = "";
        }

        return n;
    }

    public void resolveFlip()
    {

        if (swap && opStarted)
        {
            secondNum = createFlipString(secondNum);
        }
        else
        {
            firstNum = createFlipString(firstNum);
        }
    }

    public String createFlipString(String n)
    {
        String str;
        StringBuilder newstr = new StringBuilder();

        str = n;
        if(str.equals("0") || str.equals("0."))
        {
            return str;
        }
        if(!str.equals(""))
        {
            newstr.append(str.charAt(0)); //test first char to see if it's a negative
            if(!newstr.toString().equals("-"))//if its not negative
            {
                newstr.deleteCharAt(0);
                newstr.append("-");//turn it negative and start the loop
                for (int i = 0; i < str.length(); i++)
                {
                    newstr.append(str.charAt(i));
                }
                n = newstr.toString();
            }
            else//if it is negative
            {
                newstr.deleteCharAt(0);
                //delete char0 and start loop at 1
                for (int i = 1; i < str.length(); i++)
                {
                    newstr.append(str.charAt(i));
                }
                n = newstr.toString();
            }

        }
        return n;
    }

    public void resetStates()
    {
        //resets trackers with the exception of initial number and swap, used when equation is executed
        op = ""; //reset op
        opStarted = false;
        secondNum = ""; //reset secondNum
    }
    
    public String appendToString(String num, String i) //on number button, appends new digit to end of current number
    {
        if (num.contains(".") && i.equals(".")) //avoiding double decimals
        {
            return num;
        }
        else
        {
            return num+i;
        }
    }

    public void updateDisplays()
    {
        String str;
        str = firstNum + " " +  op + " " + secondNum;
        textCurrentDisplay.setText(str);
    }

}