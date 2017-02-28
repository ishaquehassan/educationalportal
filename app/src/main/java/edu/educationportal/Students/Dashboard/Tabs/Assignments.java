package edu.educationportal.Students.Dashboard.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.educationportal.Core.Adapters.AssignmentsAdapter;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.Models.Assignment;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Assignments extends BaseFragment {

    private ArrayList<Assignment> assignments;

    private DatabaseReference assignmentsRefrence;
    public Assignments() {
        setTitle("Assignments");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.students_assignment, container, false));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notifications_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        assignments = new ArrayList<>();
        final RecyclerView.Adapter adapter = new AssignmentsAdapter(assignments);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        assignmentsRefrence = FirebaseDatabase.getInstance().getReference().child("assignments");

        assignmentsRefrence.orderByChild("semester").equalTo(CoreFeatures.student.getSemester()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Assignment assignment = dataSnapshot.getValue(Assignment.class);
                        if (assignment != null) {
                            assignments.add(assignment);
                            adapter.notifyItemInserted(assignments.size() - 1);
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        return getFragmentView();
    }
}