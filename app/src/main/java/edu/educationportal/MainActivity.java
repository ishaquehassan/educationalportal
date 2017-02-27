package edu.educationportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.educationportal.Core.BaseActivity;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.Models.Student;
import edu.educationportal.Models.Teacher;
import edu.educationportal.Students.StudentsAuth;
import edu.educationportal.Students.Dashboard.StudentsDashboard;
import edu.educationportal.Teachers.TeachersAuth;
import edu.educationportal.Teachers.Dashboard.TeachersDashboard;

public class MainActivity extends BaseActivity {

    private Button stu_portal_main_btn;
    private Button tch_portal_main_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
        setupListeners();

        checkAuth();
    }

    private void setupComponents(){
        stu_portal_main_btn = (Button) findViewById(R.id.stu_portal_main_btn);
        tch_portal_main_btn = (Button) findViewById(R.id.tch_portal_main_btn);
    }

    private void setupListeners(){
        stu_portal_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudentsAuth.class));
            }
        });

        tch_portal_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeachersAuth.class));
            }
        });
    }

    private void checkAuth(){
        if(getFirebaseUser() != null){
            final ProgressDialog progressDialog = CoreFeatures.buildLoadingDialog(this,"PLease wait...");
            progressDialog.show();
            FirebaseDatabase.getInstance().getReference().child("students").child(getFirebaseUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null){
                                CoreFeatures.student = dataSnapshot.getValue(Student.class);

                                startActivity(new Intent(MainActivity.this, StudentsDashboard.class));
                                finish();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
            FirebaseDatabase.getInstance().getReference().child("teachers").child(getFirebaseUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null){
                                CoreFeatures.teacher = dataSnapshot.getValue(Teacher.class);

                                startActivity(new Intent(MainActivity.this, TeachersDashboard.class));
                                finish();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }

    }
}
