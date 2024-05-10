package com.example.a06projetos;
5

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextInitialSalary, editTextYears, editTextTotalHours, editTextHourlyRate;
    RadioButton radioButtonTitular, radioButtonHorista;
    Button buttonCalculate;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInitialSalary = findViewById(R.id.editTextInitialSalary);
        editTextYears = findViewById(R.id.editTextYears);
        editTextTotalHours = findViewById(R.id.editTextTotalHours);
        editTextHourlyRate = findViewById(R.id.editTextHourlyRate);
        radioButtonTitular = findViewById(R.id.radioButtonTitular);
        radioButtonHorista = findViewById(R.id.radioButtonHorista);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonTitular) {
                    editTextTotalHours.setVisibility(View.GONE);
                    editTextHourlyRate.setVisibility(View.GONE);
                } else if (checkedId == R.id.radioButtonHorista) {
                    editTextTotalHours.setVisibility(View.VISIBLE);
                    editTextHourlyRate.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSalary();
            }
        });
    }

    private void calculateSalary() {
        double salary;

        if (radioButtonTitular.isChecked()) {
            if (editTextYears.getText().toString().isEmpty() || editTextInitialSalary.getText().toString().isEmpty()) {
                textViewResult.setText("Por favor, insira o salário inicial e o número de anos na instituição.");
                return;
            }

            int years = Integer.parseInt(editTextYears.getText().toString());
            double initialSalary = Double.parseDouble(editTextInitialSalary.getText().toString());

            if (years >= 5) {
                double incrementPercentage = (years / 5) * 0.05;
                salary = initialSalary * (1 + incrementPercentage);
            } else {
                salary = initialSalary;
            }
        } else { // Horista
            if (editTextTotalHours.getText().toString().isEmpty() || editTextHourlyRate.getText().toString().isEmpty()) {
                textViewResult.setText("Por favor, insira o total de horas e o valor da hora aula.");
                return;
            }

            double totalHours = Double.parseDouble(editTextTotalHours.getText().toString());
            double hourlyRate = Double.parseDouble(editTextHourlyRate.getText().toString());
            salary = totalHours * hourlyRate;
        }

        textViewResult.setText("Salário Final: R$" + String.format("%.2f", salary));
    }
}
