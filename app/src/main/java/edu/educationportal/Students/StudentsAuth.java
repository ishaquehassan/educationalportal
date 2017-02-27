package edu.educationportal.Students;

import android.os.Bundle;

import java.util.ArrayList;

import edu.educationportal.Core.BaseActivity;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.Generalized.Auth.SignUp;
import edu.educationportal.R;
import edu.educationportal.Core.Generalized.Auth.Login;

public class StudentsAuth extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_auth);

        setTitle(getResources().getString(R.string.stu_portal_main_btn));

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        Login login = new Login();
        login.setTeacher(false);
        fragments.add(login);

        SignUp signUp = new SignUp();
        signUp.setTeacher(false);
        fragments.add(signUp);

        setupPagerAndTabs(fragments);

    }
}
