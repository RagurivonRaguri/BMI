package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

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

        // Result elements
        LinearLayout resultLayout = findViewById(R.id.resultLayout);
        TextView tvResult = findViewById(R.id.tvResult);
        Button exitButton = findViewById(R.id.button);

        btnCalculate.setOnClickListener(v -> {
            String weightStr = etWeight.getText().toString().trim();
            String heightStr = etHeight.getText().toString().trim();

            if (validateInputs(weightStr, heightStr)) {
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                // BMI Calculation: weight (kg) / [height (m)]^2
                double bmi = weight / (height * height);

                String category;
                if (bmi < 18.5) {
                    category = "Underweight";
                } else if (bmi < 25) {
                    category = "Normal";
                } else if (bmi < 30) {
                    category = "Overweight";
                } else {
                    category = "Obese";
                }

                tvResult.setText(String.format(Locale.getDefault(), "%.2f (%s)", bmi, category));

                // Show the entire vertical result section
                resultLayout.setVisibility(View.VISIBLE);

            } else {
                resultLayout.setVisibility(View.GONE);
            }
        });

        exitButton.setOnClickListener(v -> finishAffinity());
    }

    private boolean validateInputs(String weightStr, String heightStr) {
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            if (weight <= 0 || height <= 0) {
                Toast.makeText(this, "Weight and height must be greater than 0", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (height > 3.0) {
                Toast.makeText(this, "Height seems too large (should be in meters)", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}