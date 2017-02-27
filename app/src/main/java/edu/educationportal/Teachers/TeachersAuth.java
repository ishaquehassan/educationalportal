package edu.educationportal.Teachers;

import android.os.Bundle;

import java.util.ArrayList;

import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.Generalized.Auth.Login;
import edu.educationportal.R;
import edu.educationportal.Core.BaseActivity;

public class TeachersAuth extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_auth);

        setTitle(getResources().getString(R.string.stu_portal_main_btn));

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new Login());

        setupPagerAndTabs(fragments);

    }
}
