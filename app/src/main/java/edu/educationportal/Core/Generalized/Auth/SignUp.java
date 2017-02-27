package edu.educationportal.Core.Generalized.Auth;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.Models.Student;
import edu.educationportal.Models.Teacher;
import edu.educationportal.R;
import edu.educationportal.Students.Dashboard.StudentsDashboard;
import edu.educationportal.Teachers.Dashboard.TeachersDashboard;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class SignUp extends BaseFragment {

    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private Button btn_sign_in;

    private boolean isTeacher;

    private ProgressDialog progressDialog;

    public SignUp(){
        setTitle("Sign Up");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.signup_layout,container,false));
        setupComponents();
        setupListeners();

        return getFragmentView();
    }

    private void setupComponents(){
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);

        progressDialog = CoreFeatures.buildLoadingDialog(getContext(),"Loading...Please wait");
    }

    private void setupListeners(){
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                et_name.setError(null);
                et_email.setError(null);
                et_password.setError(null);

                if(name.length() <= 0){
                    et_name.setError("Please enter Name");
                    return;
                }

                if(email.length() <= 0){
                    et_email.setError("Please enter Email");
                    return;
                }

                if(password.length() <= 0){
                    et_password.setError("Please enter Password");
                    return;
                }

                login(name,email,password);
            }
        });
    }

    private void login(final String name, final String email, String password){
        progressDialog.show();
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();

                            FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(isTeacher()){
                                        FirebaseDatabase.getInstance().getReference().child("teachers")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(new Teacher(name,email)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                startActivity(new Intent(getContext(), TeachersDashboard.class));
                                                getActivity().finish();
                                                progressDialog.dismiss();
                                            }
                                        });
                                    }else{
                                        FirebaseDatabase.getInstance().getReference().child("students")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(new Student(name,email)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                startActivity(new Intent(getContext(), StudentsDashboard.class));
                                                getActivity().finish();
                                                progressDialog.dismiss();
                                            }
                                        });
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(getContext(),task.getException().getMessage()+"",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }
}
