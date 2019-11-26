package com.jose.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.TaskInfo;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.jose.homework1.tasks.TaskListContent;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener, DeleteDialog.OnFragmentInteractionListener {
    public static final int BUTTON_REQUEST = 1;
    public static String taskName="Name";
    public static String taskSurname="Surname";
    public static String taskBday="bday";
    public static String taskPhone="phone";
    public static String selectedSound="sound";
    public static int taskImage;

    private int currentItemPosition = -1;
    public static final String taskExtra = "taskExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public boolean onClickButton(View view){
        Intent new_entry = new Intent(getApplicationContext(), new_entry.class);
        startActivityForResult(new_entry, BUTTON_REQUEST);
        return true;
    }


    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        if (resCode == RESULT_OK) {
            if (reqCode == BUTTON_REQUEST) {
                taskName = data.getStringExtra(taskName);
                taskSurname = data.getStringExtra(taskSurname);
                taskBday = data.getStringExtra(taskBday);
                taskPhone = data.getStringExtra(taskPhone);
                selectedSound = data.getStringExtra(selectedSound);


                Toast.makeText(getApplicationContext(), "Name: "+ taskName + "Surname: "+ taskSurname + "Phone: " + taskPhone + "Birthday "+ taskBday + "Sound: " + selectedSound, Toast.LENGTH_SHORT).show();


                if (taskName.isEmpty() && taskSurname.isEmpty()) {
                    TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1, getString(R.string.defaultName), getString(R.string.defaultSurname), selectedSound));
                } else {
                    if (taskName.isEmpty()) {
                        taskName = getString(R.string.defaultName);
                    }
                    if (taskSurname.isEmpty()) {
                        taskSurname = getString(R.string.defaultSurname);
                    }

                    TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1, taskName, taskSurname, selectedSound, taskBday, taskPhone));

                    ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();


                }
            }
            if (resCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), getText(R.string.back_message), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onListDeleteClick(int position) {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
        currentItemPosition = position;
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayTaskInFragment(task);
        }
        else {

            startSecondActivity(task, position);
        }

    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        int sound;
        String soundloc = TaskListContent.ITEMS.get(position).sound;
        switch(soundloc){
            case "Sound 1":
                sound = R.raw.sound1;
                break;
            case "Sound 2":
                sound = R.raw.sound2;
                break;
            case "Sound 3":
                sound = R.raw.sound3;
                break;
            case "Sound 4":
                sound = R.raw.sound4;
                break;
            case "Sound 5":
                sound = R.raw.sound5;
                break;
            case "Sound 6":
                sound = R.raw.sound6;
                break;
            default:
                sound = R.raw.sound1;
        }

        final MediaPlayer mp = MediaPlayer.create(this, sound);
        mp.start();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition< TaskListContent.ITEMS.size()){
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void displayTaskInFragment(TaskListContent.Task task){
        TaskInfoFragment taskInfoFragment =  ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(taskInfoFragment != null){
            taskInfoFragment.displayTask(task);
        }
    }

    private void startSecondActivity(TaskListContent.Task task, int position){
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra(taskExtra, (Parcelable) task);
        startActivity(intent);
    }
}
