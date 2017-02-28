package edu.educationportal.Core.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.educationportal.Models.Assignment;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {
    ArrayList<Assignment> assignments = new ArrayList<>();

    public AssignmentsAdapter(ArrayList<Assignment> assignments){
        this.assignments = assignments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.title.setText(assignment.getTitle());
        holder.descp.setText(assignment.getDescp());
        holder.notification_teacher.setText("Teacher: "+assignment.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView title;
        TextView descp;
        TextView notification_teacher;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView) itemView.findViewById(R.id.notification_title);
            descp = (TextView) itemView.findViewById(R.id.notification_descp);
            notification_teacher = (TextView) itemView.findViewById(R.id.notification_teacher);
        }
    }
}
