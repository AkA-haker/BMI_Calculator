package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button calculate;
    private TextView appName;
    private TextView resultText;
    private EditText editAge;
    private EditText editFeet;
    private EditText editInches;
    private EditText editWeight;
    private RadioButton maleButton;
    private RadioButton femaleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String st = "HELLO";
        Toast.makeText(this, st, Toast.LENGTH_SHORT).show();

        changeFunctions();
        onclick();
    }private void changeFunctions() {

        appName = findViewById(R.id.text_view_appname);
        appName.setText("WELCOME TO BMI CALCULATOR");
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        editAge = findViewById(R.id.edit_text_age);
        editFeet = findViewById(R.id.edit_text_feet);
        editInches = findViewById(R.id.edit_text_inches);
        editWeight = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void onclick() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double bmi = calculateButtonWork();
                String ageCollector = editAge.getText().toString();
                int age = Integer.parseInt(ageCollector);
                if (age >= 18) {
                    displayResult(bmi);
                } else {
                    under18GuidLine(bmi);
                }
            }
        });
    }


    private double calculateButtonWork() {
        //to store the user input values
        String feetCollector = editFeet.getText().toString();
        String inchesCollector = editInches.getText().toString();
        String weightCollector = editWeight.getText().toString();

        //to convert String to int
        int feet = Integer.parseInt(feetCollector);
        int inches = Integer.parseInt(inchesCollector);
        int weight = Integer.parseInt(weightCollector);
        // Calculating
        int totalInches = (feet * 12) + inches;
        double totalHeight = totalInches * 0.0254;
        return weight / (totalHeight * totalHeight);
    }

    private void displayResult(double bmi) {
        //to convart any variable type into String
        // String bmiResult = String.valueOf(bmi);
        DecimalFormat conveter = new DecimalFormat("0.00");
        String bmiResult = conveter.format(bmi);
        String calculatingText;
        if (bmi < 18.5) {
            calculatingText = bmiResult + "-You are under weight";
        } else if (bmi > 25) {
            calculatingText = bmiResult + "- you are over weight ";
        } else {
            calculatingText = bmiResult + "- you are healty weight";
        }
        resultText.setText(calculatingText);
    }

    private void under18GuidLine(double bmi) {
        DecimalFormat conveter = new DecimalFormat("0.00");
        String bmiResult = conveter.format(bmi);
        String calculatingText;
        if (maleButton.isChecked()) {
            calculatingText = bmiResult + "- As you are under 18, please consult with your doctor for healthy range for Boys ";
        } else if (femaleButton.isChecked()) {
            calculatingText = bmiResult + "- As you are under 18, please consult with your doctor for healthy range for Girls ";

        } else
            calculatingText = bmiResult + "- As you are under 18, please consult with your doctor for healthy range ";


        resultText.setText(calculatingText);
    }


}