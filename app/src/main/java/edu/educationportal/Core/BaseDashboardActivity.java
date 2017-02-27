package edu.educationportal.Core;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.educationportal.MainActivity;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class BaseDashboardActivity extends BaseActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.logout:
                getFirebaseAuth().signOut();
                startActivity(new Intent(BaseDashboardActivity.this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }

        return true;
    }
}
