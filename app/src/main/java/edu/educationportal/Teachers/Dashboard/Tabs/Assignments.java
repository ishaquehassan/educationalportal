package edu.educationportal.Teachers.Dashboard.Tabs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.educationportal.Core.Adapters.AssignmentsAdapter;
import edu.educationportal.Core.BaseActivity;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.Models.Assignment;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Assignments extends BaseFragment {

    private ArrayList<Assignment> assignments;

    private EditText et_title;
    private EditText et_descp;
    private Button save_notification;
    private DatabaseReference assignmentsRefrence;
    private RadioButton firstSem;
    private RadioButton secondSem;
    private RadioButton thirdSem;
    private RadioButton fourthSem;
    private RadioButton fifthSem;
    private RadioButton sixthSem;

    public Assignments() {
        setTitle("Assignments");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.teachers_assignment, container, false));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notifications_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        assignments = new ArrayList<>();
        final RecyclerView.Adapter adapter = new AssignmentsAdapter(assignments);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        assignmentsRefrence = FirebaseDatabase.getInstance().getReference().child("assignments");

        assignmentsRefrence.orderByChild("teacherId").equalTo(((BaseActivity)getActivity()).getFirebaseUser().getUid()).addChildEventListener(new ChildEventListener() {
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

        setupComponents();
        setupListeners();

        return getFragmentView();
    }

    private void setupComponents(){
        et_title = (EditText) findViewById(R.id.et_title);
        et_descp = (EditText) findViewById(R.id.et_descp);

        firstSem = (RadioButton) findViewById(R.id.firstSem);
        secondSem = (RadioButton) findViewById(R.id.secondSem);
        thirdSem = (RadioButton) findViewById(R.id.thirdSem);
        fourthSem = (RadioButton) findViewById(R.id.fourthSem);
        fifthSem = (RadioButton) findViewById(R.id.fifthSem);
        sixthSem = (RadioButton) findViewById(R.id.sixthSem);

        save_notification = (Button) findViewById(R.id.save_notification);
    }

    private void setupListeners(){
        save_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String descp = et_descp.getText().toString();

                et_title.setError(null);
                et_descp.setError(null);

                if(title.length() <= 0){
                    et_title.setError("Please enter title");
                    return;
                }

                if(descp.length() <= 0){
                    et_descp.setError("Please enter Details");
                    return;
                }

                if(!firstSem.isChecked() && !secondSem.isChecked() && !thirdSem.isChecked() && !fourthSem.isChecked() && !fifthSem.isChecked() && !sixthSem.isChecked()) {
                    Toast.makeText(getContext(), "Please Select a semester to save assignment", Toast.LENGTH_SHORT).show();
                    return;
                }

                int sem = 0;
                if(firstSem.isChecked()){
                    sem = 1;
                }else if(secondSem.isChecked()){
                    sem = 2;
                }else if(thirdSem.isChecked()){
                    sem = 3;
                }else if(fourthSem.isChecked()){
                    sem = 4;
                }else if(fifthSem.isChecked()){
                    sem = 5;
                }else if(sixthSem.isChecked()){
                    sem = 6;
                }

                final ProgressDialog progressDialog = CoreFeatures.buildLoadingDialog(getContext(),"Saving...");
                progressDialog.show();

                Assignment assignment = new Assignment(title,descp,((BaseActivity)getActivity()).getFirebaseUser().getDisplayName(),sem,((BaseActivity)getActivity()).getFirebaseUser().getUid());
                assignmentsRefrence.push().setValue(assignment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            et_title.setText("");
                            et_descp.setText("");
                            firstSem.setChecked(false);
                            secondSem.setChecked(false);
                            thirdSem.setChecked(false);
                            fourthSem.setChecked(false);
                            fifthSem.setChecked(false);
                            sixthSem.setChecked(false);

                            Toast.makeText(getContext(),"SAVED!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}