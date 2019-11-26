package com.jose.homework1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class show_details extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.details);

        TextView taskNameInput = findViewById(R.id.entryTitle);
        TextView taskPhoneInput = findViewById(R.id.taskPhone);
        TextView taskBdayInput = findViewById(R.id.taskBday);
        TextView taskSoundInput = findViewById(R.id.taskSound);
        ImageView taskImageInput = findViewById(R.id.taskImage);
        String taskImage="" ;



        super.onCreate(savedInstanceState);

        Intent received_intent = getIntent();
        int image = received_intent.getIntExtra(taskImage,MainActivity.taskImage);
        String taskBday = received_intent.getStringExtra(MainActivity.taskBday);
        String taskTitle = received_intent.getStringExtra(MainActivity.taskName);
        String taskSound = received_intent.getStringExtra(MainActivity.selectedSound);
        String taskPhone = received_intent.getStringExtra(MainActivity.taskPhone);

        taskNameInput.setText(taskTitle);
        taskPhoneInput.setText(taskPhone);
        taskBdayInput.setText(taskBday);
        taskSoundInput.setText(taskSound);
        taskImageInput.setImageDrawable(getApplicationContext().getDrawable(image));

    }



}
