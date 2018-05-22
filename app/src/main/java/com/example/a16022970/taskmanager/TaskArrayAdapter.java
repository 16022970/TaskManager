package com.example.a16022970.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView tvName;
    TextView tvDesc;

    public TaskArrayAdapter(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvName = (TextView)rowView.findViewById(R.id.tvName);
        tvDesc = (TextView)rowView.findViewById(R.id.tvDesc);

        Task task = tasks.get(position);

        tvName.setText(task.getId() + " " + String.valueOf(task.getName()));
        tvDesc.setText(task.getDesc());

        return rowView;
    }


}
