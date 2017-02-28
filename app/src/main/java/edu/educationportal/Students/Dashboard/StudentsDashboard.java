package edu.educationportal.Students.Dashboard;

import android.os.Bundle;

import java.util.ArrayList;

import edu.educationportal.Core.BaseDashboardActivity;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.R;
import edu.educationportal.Students.Dashboard.Tabs.Notifications;
import edu.educationportal.Students.Dashboard.Tabs.Assignments;
import edu.educationportal.Students.Dashboard.Tabs.UpdateProfile;

public class StudentsDashboard extends BaseDashboardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_dashboard);
        setTitle(getFirebaseUser().getDisplayName()+" Dashboard");
        getSupportActionBar().setSubtitle("Students Dashboard");

        ArrayList<BaseFragment> pages = new ArrayList<>();
        pages.add(new Notifications());
        pages.add(new Assignments());
        pages.add(new UpdateProfile());

        setupPagerAndTabs(pages);
    }

}
