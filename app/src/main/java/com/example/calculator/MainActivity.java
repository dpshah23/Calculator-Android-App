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

    Button multiply,addition,divide,percentage,dot,del,clear,subtraction;

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
        subtraction=findViewById(R.id.btnSubtract)
    }
}