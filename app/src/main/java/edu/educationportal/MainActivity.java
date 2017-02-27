package edu.educationportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.educationportal.Students.StudentsAuth;
import edu.educationportal.Teachers.TeachersAuth;
import edu.educationportal.Core.BaseActivity;

public class MainActivity extends BaseActivity {

    private Button stu_portal_main_btn;
    private Button tch_portal_main_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
        setupListeners();
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
}
