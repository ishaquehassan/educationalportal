package edu.educationportal.Core.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import edu.educationportal.Core.BaseFragment;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<BaseFragment> fragments;

    public ViewPagerAdapter(FragmentManager fm,ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
