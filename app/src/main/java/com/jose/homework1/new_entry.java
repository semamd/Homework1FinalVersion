package com.jose.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class new_entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_element);

    }


    public void submitClick(View view) {
        EditText taskTitleEditTxt = findViewById(R.id.taskTitle);
        EditText taskSurnameEditTxt = findViewById(R.id.taskDescription);
        EditText taskBdayEditText = findViewById(R.id.taskBirthday);
        EditText taskTelephoneEditText = findViewById(R.id.taskPhone);
        Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
        String taskNewTitle = taskTitleEditTxt.getText().toString();
        String taskNewSurname = taskSurnameEditTxt.getText().toString();
        String newSelectedSound = drawableSpinner.getSelectedItem().toString();
        String taskNewPhone = taskTelephoneEditText.getText().toString();
        String taskNewBday = taskBdayEditText.getText().toString();




        boolean phoneValid = false;
        boolean dateValid = false;

        if(validatePhone(taskNewPhone)){
            phoneValid = true;
        }


        if(validateDate(taskNewBday)){
            dateValid = true;
        }



        if(phoneValid == true && dateValid == true){
            if(taskNewTitle.isEmpty() == false  && taskNewSurname.isEmpty() == false) {
                Intent data = new Intent();
                data.putExtra(MainActivity.taskName, taskNewTitle);
                data.putExtra(MainActivity.selectedSound, newSelectedSound);
                data.putExtra(MainActivity.taskPhone, taskNewPhone);
                data.putExtra(MainActivity.taskBday, taskNewBday);
                data.putExtra(MainActivity.taskSurname, taskNewSurname);

                setResult(RESULT_OK, data);
                finish();
            }
        }

        taskTitleEditTxt.setText("");
        taskSurnameEditTxt.setText("");
        taskBdayEditText.setText("");
        taskTelephoneEditText.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


    }

    private static boolean validatePhone(String phone){
        boolean response = false;
        if(phone.matches(("\\d{9}"))) {
            response = true;
        }
        return response;
    }

    private static boolean validateDate(String date){
        boolean response = false;
        if (Pattern.matches("[a-zA-Z]+", date) == false && date.length() == 10) {
            response = true;
        }
        return response;
    }
}

