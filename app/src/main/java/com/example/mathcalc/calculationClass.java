package com.example.mathcalc;

import java.text.DecimalFormat;

public class calculationClass
{
    public String calculate (double leftNum, String op, double rightNum)
    {
        double calc = 0;
        DecimalFormat format = new DecimalFormat("0.#############"); //https://stackoverflow.com/questions/14204905/java-how-to-remove-trailing-zeros-from-a-double
        switch (op)
        {
            case "*":
               calc = leftNum * rightNum;
               break;
            case "+":
                calc = leftNum + rightNum;
                break;
            case "-":
                calc = leftNum - rightNum;
                break;
            case "/":
                if (rightNum == 0.0)
                {
                    return "NaN";
                }
                calc = leftNum / rightNum;
                break;
        }
        return format.format(calc)+"";
    }
}
