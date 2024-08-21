package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        clear.setOnClickListener(v -> {
            display.setText("");
        });

        del.setOnClickListener(v -> deleteLastchar());


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
        dispequation+=op;
        display.setText(dispequation);
    }

}