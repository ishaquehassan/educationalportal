package edu.educationportal.Teachers;

import android.os.Bundle;

import java.util.ArrayList;

import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.Generalized.Auth.Login;
import edu.educationportal.Core.Generalized.Auth.SignUp;
import edu.educationportal.R;
import edu.educationportal.Core.BaseActivity;

public class TeachersAuth extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_auth);

        setTitle(getResources().getString(R.string.tch_portal_main_btn));

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        Login login = new Login();
        login.setTeacher(true);
        fragments.add(login);

        SignUp signUp = new SignUp();
        signUp.setTeacher(true);
        fragments.add(signUp);

        setupPagerAndTabs(fragments);

    }
}
