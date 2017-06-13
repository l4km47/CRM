package com.rcis.CRM;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by lakmal 2017-06-05
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
    }

    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_seearch, container, false);

        return rootView;
    }
}
