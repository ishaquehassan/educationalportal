package edu.educationportal.Core;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class BaseFragment extends Fragment {

    private View fragmentView;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public View getFragmentView() {
        return fragmentView;
    }

    public void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    protected View findViewById(int res){
        return getFragmentView().findViewById(res);
    }
}
