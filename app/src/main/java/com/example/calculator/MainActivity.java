package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {



    TextView display;

    Button btnno1,btnno2,btnno3,btnno4,btnno5,btnno6,btnno7,btnno8,btnno9,btnno0;

    Button multiply,addition,divide,percentage,dot,del,clear,subtraction,equal;

    String dispequation = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnno1=findViewById(R.id.btn1);
        btnno2=findViewById(R.id.btn2);
        btnno3=findViewById(R.id.btn3);
        btnno4=findViewById(R.id.btn4);
        btnno5=findViewById(R.id.btn5);
        btnno6=findViewById(R.id.btn6);
        btnno7=findViewById(R.id.btn7);
        btnno8=findViewById(R.id.btn8);
        btnno9=findViewById(R.id.btn9);
        btnno0=findViewById(R.id.btn0);

        addition=findViewById(R.id.btnAdd);
        multiply=findViewById(R.id.btnMultiply);
        divide=findViewById(R.id.btnDivide);
        subtraction=findViewById(R.id.btnSubtract);

        equal=findViewById(R.id.btnEquals);

        del=findViewById(R.id.btnDelete);

        percentage=findViewById(R.id.btnPercentage);
        clear=findViewById(R.id.btnClear);

        dot=findViewById(R.id.btnDot);

        display=findViewById(R.id.tvDisplay);

        String dispequation = "";


        btnno0.setOnClickListener(v -> appendDigit("0"));
        btnno1.setOnClickListener(v -> appendDigit("1"));
        btnno2.setOnClickListener(v -> appendDigit("2"));
        btnno3.setOnClickListener(v -> appendDigit("3"));
        btnno4.setOnClickListener(v -> appendDigit("4"));
        btnno5.setOnClickListener(v -> appendDigit("5"));
        btnno6.setOnClickListener(v -> appendDigit("6"));
        btnno7.setOnClickListener(v -> appendDigit("7"));
        btnno8.setOnClickListener(v -> appendDigit("8"));
        btnno9.setOnClickListener(v -> appendDigit("9"));
        dot.setOnClickListener(v -> appendDigit("."));

        addition.setOnClickListener(v -> setOperator("+"));
        multiply.setOnClickListener(v -> setOperator("*"));
        subtraction.setOnClickListener(v -> setOperator("-"));
        divide.setOnClickListener(v -> setOperator("/"));
        percentage.setOnClickListener(v -> setOperator("%"));

        clear.setOnClickListener(v -> clearfull());

        del.setOnClickListener(v -> deleteLastchar());

        equal.setOnClickListener(v -> CalculateResults());


    }

    private  void clearfull(){
        dispequation="";
        display.setText("");
    }
    private void deleteLastchar(){

        if (!dispequation.isEmpty()) {
            dispequation = dispequation.substring(0, dispequation.length() - 1);
            display.setText(dispequation.isEmpty() ? "0" : dispequation);
        }

    }

    private void appendDigit(String digit) {
        dispequation += digit;
        display.setText(dispequation);
    }

    private void setOperator(String op) {

        char lastChar = dispequation.charAt(dispequation.length() - 1);


        if(lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/')
        {
            Toast.makeText(MainActivity.this, "Math Error...", Toast.LENGTH_SHORT).show();
        }
        else{

        dispequation+=op;
        display.setText(dispequation);
    }
    }

    private void CalculateResults() {
        try {
            double result = solveEquation(dispequation);
            display.setText(String.valueOf(result));
            dispequation = String.valueOf(result);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Math Error...", Toast.LENGTH_SHORT).show();
            Log.e("Calculator", "Error in calculation", e);
        }
    }

    private double solveEquation(String equation) {

        String postfix = infixToPostfix(equation);

        return evaluatePostfix(postfix);
    }

    private String infixToPostfix(String equation) {
        StringBuilder output = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);


            if (Character.isDigit(c) || c == '.') {
                output.append(c);
            } else {
                output.append(' ');


                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    output.append(operators.pop()).append(' ');
                }
                operators.push(c);
            }
        }


        while (!operators.isEmpty()) {
            output.append(' ').append(operators.pop());
        }

        return output.toString();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '%':
                return 3;
            default:
                return -1;
        }
    }

    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result;

                switch (token.charAt(0)) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = operand1 / operand2;
                        break;
                    case '%':
                        result = operand1 % operand2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }

                stack.push(result);
            }
        }

        return stack.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}