package edu.educationportal.Students.Dashboard.Tabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import edu.educationportal.Core.BaseActivity;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.MainActivity;
import edu.educationportal.Models.Student;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class UpdateProfile extends BaseFragment {

    private EditText et_name;
    private Button save_profile;
    private RadioButton firstSem;
    private RadioButton secondSem;
    private RadioButton thirdSem;
    private RadioButton fourthSem;
    private RadioButton fifthSem;
    private RadioButton sixthSem;

    public UpdateProfile() {
        setTitle("Update Profile");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.student_update_profile, container, false));

        setupComponents();
        setupListeners();

        return getFragmentView();
    }

    private void setupComponents(){
        et_name = (EditText) findViewById(R.id.et_name);

        et_name.setText(CoreFeatures.student.getName());

        firstSem = (RadioButton) findViewById(R.id.firstSem);
        secondSem = (RadioButton) findViewById(R.id.secondSem);
        thirdSem = (RadioButton) findViewById(R.id.thirdSem);
        fourthSem = (RadioButton) findViewById(R.id.fourthSem);
        fifthSem = (RadioButton) findViewById(R.id.fifthSem);
        sixthSem = (RadioButton) findViewById(R.id.sixthSem);

        switch (CoreFeatures.student.getSemester()){
            case 1:
                firstSem.setChecked(true);
                break;
            case 2:
                secondSem.setChecked(true);
                break;
            case 3:
                thirdSem.setChecked(true);
                break;
            case 4:
                fourthSem.setChecked(true);
                break;
            case 5:
                fifthSem.setChecked(true);
                break;
            case 6:
                sixthSem.setChecked(true);
                break;
        }

        save_profile = (Button) findViewById(R.id.save_notification);
    }

    private void setupListeners(){
        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();

                et_name.setError(null);

                if(name.length() <= 0){
                    et_name.setError("Please enter Name");
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

                final Student student = CoreFeatures.student;
                student.setName(name);
                student.setSemester(sem);

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name).build();
                FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference().child("students").child(((BaseActivity)getActivity()).getFirebaseUser().getUid()).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        Toast.makeText(getContext(),"SAVED!",Toast.LENGTH_SHORT).show();
                                        getActivity().startActivity(new Intent(getContext(), MainActivity.class));
                                        getActivity().finish();
                                    }else{
                                        Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });


            }
        });
    }
}