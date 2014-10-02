package net.brusd.phonecontroller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.brusd.phonecontroller.R;

/**
 * Created by BruSD on 6/7/2014.
 */
public class AboutFragment extends Fragment {

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_about, container, false);
        return rootView;
    }
}
