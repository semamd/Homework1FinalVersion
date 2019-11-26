package com.jose.homework1;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jose.homework1.tasks.TaskListContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskInfoFragment extends Fragment {


    public TaskInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    public void displayTask(TaskListContent.Task task){
        FragmentActivity activity = getActivity();

        TextView taskInfoName = activity.findViewById(R.id.entryTitle);
        int n = task.n;
        TextView taskInfoPhone = activity.findViewById(R.id.taskPhone);
        TextView taskInfoBday = activity.findViewById(R.id.taskBday);
        ImageView taskInfoImage = activity.findViewById(R.id.taskImage);
        TextView taskInfoSound = activity.findViewById(R.id.taskSound);
        taskInfoName.setText(task.username + " " + task.surname);
        taskInfoPhone.setText(task.telephone);
        taskInfoBday.setText(task.bday);
        taskInfoSound.setText(task.sound);
        int taskImage;
        switch (n){
            case 1:
                taskImage = R.drawable.pic1;
                break;
            case 2:
                taskImage = R.drawable.pic2;
                break;
            case 3:
                taskImage = R.drawable.pic3;
                break;
            case 4:
                taskImage = R.drawable.pic4;
                break;
            case 5:
                taskImage = R.drawable.pic5;
                break;
            case 6:
                taskImage = R.drawable.pic6;
                break;
             default:
                 taskImage = R.drawable.pic1;
        }

        taskInfoImage.setImageDrawable(activity.getResources().getDrawable(taskImage));

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        if(intent != null){
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedTask != null){
                displayTask(receivedTask);
            }
        }
    }

}
