package com.gcpcx.formvalidation.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.gcpcx.formvalidation.R;
import com.gcpcx.formvalidation.data.model.Form;

import com.gcpcx.formvalidation.databinding.ActivityFormBinding;
import com.gcpcx.formvalidation.domain.MainActivityViewModel;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityFormBinding binding;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createAlertDialog();
        createSpinner();


        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.validationName.observe(this, validation -> {
            binding.layoutName.setError(validation.getMessage());
        });

        mainActivityViewModel.validationEmail.observe(this,validation -> {
            binding.layoutEmail.setError(validation.getMessage());
        });

        mainActivityViewModel.validationMobileNumber.observe(this,validation -> {
            binding.layoutNumber.setError(validation.getMessage());
        });

        mainActivityViewModel.validationAge.observe(this,validation -> {
            binding.layoutAge.setError(validation.getMessage());
        });

        mainActivityViewModel.formResponse.observe(this,formResponse -> {
            if (formResponse!=null){
                alertDialog.setTitle("Mocky.io response");
                alertDialog.setMessage(formResponse.getMessage());
                alertDialog.show();
            }
        });

        binding.calendarPicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
            calculateAge();
        });

        binding.buttonSubmit.setOnClickListener(v -> mainActivityViewModel.submitForm(
                new Form(
                        binding.layoutName.getEditText().getText().toString(),
                        binding.layoutAge.getEditText().getText().toString(),
                        binding.layoutEmail.getEditText().getText().toString(),
                        binding.layoutNumber.getEditText().getText().toString()
                )
        ));
    }

    private void createSpinner(){
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerGender.setAdapter(arrayAdapter);
    }

    private void calculateAge(){
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH) +1;
        int dobYear = binding.calendarPicker.getYear();
        int dobMonth = binding.calendarPicker.getMonth() + 1;

        int age = currentYear - dobYear;
        if (currentMonth < dobMonth){
            age--;
        }
        binding.layoutAge.getEditText().setText(String.valueOf(age));
    }

    private void createAlertDialog() {
        alertDialog = new AlertDialog.Builder(this)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setTitle("Form Error")
                .create();
    }

}