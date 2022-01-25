package com.example.tasklist.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasklist.R;
import com.example.tasklist.adapters.TaskListAdapter;
import com.example.tasklist.models.TaskListModel;

public class TaskListFragment extends Fragment {
    private TaskListModel viewModel;
    private RecyclerView taskList;

    public TaskListFragment() {
        // Required empty public constructor
        viewModel = new TaskListModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasklist_fragment , container , false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.taskList = (RecyclerView) view.findViewById(R.id.taskList);

        TaskListAdapter adapter = new TaskListAdapter(viewModel.getTaskList());
        viewModel.setAdapter(adapter);
        taskList.setAdapter(adapter);
        taskList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
