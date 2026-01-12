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
import android.widget.Toast;


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

        btnCalculate.setOnClickListener(v -> {
            String weightStr = etWeight.getText().toString();
            String heightStr = etHeight.getText().toString();

            if (validateInputs(weightStr, heightStr)) {
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                double bmi = weight / (height * height);

                tvResult.setText(String.format("BMI: %.2f", bmi));
            }
        });
    }

    private boolean validateInputs(String weightStr, String heightStr) {

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            // Parse input values to double
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            // Check whether height and weight are greater than zero
            if (weight <= 0 || height <= 0) {
                Toast.makeText(this, "Weight and height must be greater than 0", Toast.LENGTH_SHORT).show();
                return false;
            }
            //Height limit
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