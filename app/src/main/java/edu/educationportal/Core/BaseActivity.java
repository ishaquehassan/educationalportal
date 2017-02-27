package edu.educationportal.Core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.educationportal.Core.Adapters.ViewPagerAdapter;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setupPagerAndTabs(ArrayList<BaseFragment> fragments){
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(fragments.size());
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return false;
        }else{
            return true;
        }
    }
}
