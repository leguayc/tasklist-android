package com.example.tasklist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tasklist.R;
import com.example.tasklist.models.Task;
import com.example.tasklist.models.TaskListModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView date;
        private final CheckBox checkBox;
        private final Button buttonDelete;
        private Task task;

        public ViewHolder(View view, TaskListModel model) {
            super(view);
            // Define click listener for the ViewHolder's View

            title = (TextView) view.findViewById(R.id.tasktitle);
            date = (TextView) view.findViewById(R.id.taskdate);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            buttonDelete = (Button) view.findViewById(R.id.buttonDelete);
            this.task = null;

            checkBox.setOnClickListener(new View.OnClickListener() {
                private TaskListModel model;

                @Override
                public void onClick(View view) {
                    boolean checked = ((CheckBox) view).isChecked();

                    if (task != null)
                        this.model.setTaskDone(task, checked);
                }

                public View.OnClickListener init(TaskListModel model) {
                    this.model = model;

                    return this;
                }
            }.init(model));


            buttonDelete.setOnClickListener(new View.OnClickListener() {
                private TaskListModel model;

                @Override
                public void onClick(View view) {
                    if (task != null)
                        this.model.removeTask(task);
                }

                public View.OnClickListener init(TaskListModel model) {
                    this.model = model;

                    return this;
                }
            }.init(model));
        }

        public TextView getTitleTextView() {
            return title;
        }

        public TextView getDateTextView() {
            return date;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setTask(Task task) {
            this.task = task;
        }
    }

    private final List<Task> taskList;

    // Pass in the contact array into the constructor
    public TaskListAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_item, viewGroup, false);

        return new ViewHolder(view, new TaskListModel().setAdapter(this));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(taskList.get(position).getTitle());

        SimpleDateFormat formater =  new SimpleDateFormat("dd-mm-yyyy");
        String dateformat = formater.format(taskList.get(position).getDate().getTime());

        viewHolder.getDateTextView().setText(dateformat);
        viewHolder.getCheckBox().setChecked(taskList.get(position).isDone());
        viewHolder.setTask(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
