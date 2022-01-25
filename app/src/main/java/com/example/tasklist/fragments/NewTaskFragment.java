package com.example.tasklist.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasklist.R;
import com.example.tasklist.models.Task;
import com.example.tasklist.models.TaskListModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTaskFragment extends Fragment {
    private DatePicker datePicker;
    private EditText editTextTitle;
    private Button buttonSubmit;
    private Button buttonReset;
    private TaskListModel viewModel;

    public NewTaskFragment() {
        // Required empty public constructor
        viewModel = new TaskListModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newtask_fragment , container , false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.datePicker = (DatePicker) getView().findViewById(R.id.datePicker);
        this.editTextTitle = (EditText) getView().findViewById(R.id.editTextTaskTitle);
        this.buttonSubmit = (Button) getView().findViewById(R.id.button3);
        this.buttonReset = (Button) getView().findViewById(R.id.button4);

        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            private NewTaskFragment newTaskFragment;

            @Override
            public void onClick(View view) {
                newTaskFragment.createTask();
                showDate();
            }

            public View.OnClickListener init(NewTaskFragment newTaskFragment) {
                this.newTaskFragment = newTaskFragment;

                return this;
            }
        }.init(this));

        this.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTitle.setText("");
            }
        });
    }

    public void showDate(){
        Calendar calendar = getCalendarFromDatePicker();
        SimpleDateFormat formater =  new SimpleDateFormat("dd-MM-yyyy");
        String dateformat = formater.format(calendar.getTime());
        String title = this.editTextTitle.getText().toString();

        Toast.makeText(getContext(), title + " Date: " + dateformat, Toast.LENGTH_LONG).show();
    }

    public void createTask() {
        Date date = getCalendarFromDatePicker().getTime();
        String title = this.editTextTitle.getText().toString();

        this.viewModel.addTask(new Task(title, date));
    }

    private Calendar getCalendarFromDatePicker() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        System.out.println("Month : " + month);
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }
}
