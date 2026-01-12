package com.example.bmi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

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
        EditText etWeight = findViewById(R.id.etWeight);
        EditText etHeight = findViewById(R.id.etHeight);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        TextView tvResult = findViewById(R.id.tvResult);
        TextView tvStatus = findViewById(R.id.tvStatus);


        btnCalculate.setOnClickListener(v -> {
            String weightStr = etWeight.getText().toString();
            String heightStr = etHeight.getText().toString();

            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                tvResult.setText("BMI: --");
                tvStatus.setText("Status: --");
                return;
            }

            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            if (weight <= 0 || height <= 0) {
                tvResult.setText("BMI: --");
                tvStatus.setText("Status: --");
                return;
            }

            double bmi = weight / (height * height);

            // Display BMI value
            tvResult.setText(String.format("BMI: %.2f", bmi));

            // Display BMI category
            String status;
            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi < 25) {
                status = "Normal";
            } else if (bmi < 30) {
                status = "Overweight";
            } else {
                status = "Obese";
            }
            tvStatus.setText("Status: " + status);
        });


        Button exitButton = findViewById(R.id.button);

        exitButton.setOnClickListener(v -> {
            finishAffinity();
        });

    }
}