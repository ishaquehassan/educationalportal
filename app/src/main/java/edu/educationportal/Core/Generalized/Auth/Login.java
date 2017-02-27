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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.educationportal.Core.BaseActivity;
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

public class Login extends BaseFragment {

    private EditText et_email;
    private EditText et_password;
    private Button btn_sign_in;

    private boolean isTeacher;

    private ProgressDialog progressDialog;

    public Login(){
        setTitle("Login");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.login_layout,container,false));
        setupComponents();
        setupListners();

        return getFragmentView();
    }

    private void setupComponents(){
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);

        progressDialog = CoreFeatures.buildLoadingDialog(getContext(),"Loading...Please wait");
    }

    private void setupListners(){
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                et_email.setError(null);
                et_password.setError(null);

                if(email.length() <= 0){
                    et_email.setError("Please enter Email");
                    return;
                }

                if(password.length() <= 0){
                    et_password.setError("Please enter Password");
                    return;
                }

                login(email,password);
            }
        });
    }

    private void login(String email,String password){
        progressDialog.show();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(isTeacher()){
                                FirebaseDatabase.getInstance().getReference().child("teachers").child(((BaseActivity)getActivity()).getFirebaseUser().getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.getValue() != null){
                                                    CoreFeatures.teacher = dataSnapshot.getValue(Teacher.class);

                                                    startActivity(new Intent(getContext(), TeachersDashboard.class));
                                                    getActivity().finish();
                                                }else{
                                                    ((BaseActivity)getActivity()).getFirebaseAuth().signOut();
                                                    Toast.makeText(getContext(),getResources().getString(R.string.invalid_sign_in_toast),Toast.LENGTH_SHORT).show();
                                                }
                                                progressDialog.dismiss();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }else{
                                FirebaseDatabase.getInstance().getReference().child("students").child(((BaseActivity)getActivity()).getFirebaseUser().getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.getValue() != null){
                                                    CoreFeatures.student = dataSnapshot.getValue(Student.class);

                                                    startActivity(new Intent(getContext(), StudentsDashboard.class));
                                                    getActivity().finish();
                                                }else{
                                                    ((BaseActivity)getActivity()).getFirebaseAuth().signOut();
                                                    Toast.makeText(getContext(),getResources().getString(R.string.invalid_sign_in_toast),Toast.LENGTH_SHORT).show();
                                                }
                                                progressDialog.dismiss();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }
                        }else {
                            Toast.makeText(getContext(),getResources().getString(R.string.invalid_sign_in_toast),Toast.LENGTH_SHORT).show();
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
