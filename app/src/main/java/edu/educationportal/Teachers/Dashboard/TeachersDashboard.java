package edu.educationportal.Teachers.Dashboard;

import android.os.Bundle;

import java.util.ArrayList;

import edu.educationportal.Core.BaseDashboardActivity;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.R;
import edu.educationportal.Teachers.Dashboard.Tabs.Assignments;
import edu.educationportal.Teachers.Dashboard.Tabs.Notifications;

public class TeachersDashboard extends BaseDashboardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_dashboard);
        setTitle(getFirebaseUser().getDisplayName()+" Dashboard");
        getSupportActionBar().setSubtitle("Teachers Dashboard");


        ArrayList<BaseFragment> pages = new ArrayList<>();
        pages.add(new Notifications());
        pages.add(new Assignments());

        setupPagerAndTabs(pages);

    }
}
