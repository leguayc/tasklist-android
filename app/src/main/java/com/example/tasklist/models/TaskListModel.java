package com.example.tasklist.models;

import com.example.tasklist.adapters.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskListModel {
    private static final List<Task> taskList = new ArrayList<Task>();
    private static TaskListAdapter adapter;

    public TaskListModel() {
        super();
    }

    public void addTask(Task task) {
        taskList.add(task);

        if (adapter != null)
            adapter.notifyItemInserted(taskList.size()-1);
    }

    public void removeTask(Task task) {
        int index = taskList.indexOf(task);
        taskList.remove(task);

        if (adapter != null)
            adapter.notifyItemRemoved(index);
    }

    public void setTaskDone(Task task, boolean isDone)
    {
        int index = taskList.indexOf(task);

        if (isDone)
        {
            taskList.get(index).setDone();
        } else
        {
            taskList.get(index).setUndone();
        }

        if (adapter != null)
            adapter.notifyItemChanged(index);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public TaskListModel setAdapter(TaskListAdapter adapter)
    {
        this.adapter = adapter;

        return this;
    }
}
