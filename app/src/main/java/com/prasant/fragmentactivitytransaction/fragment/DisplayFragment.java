package com.prasant.fragmentactivitytransaction.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prasant.fragmentactivitytransaction.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    TextView tv;


    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        tv = (TextView) rootView.findViewById(R.id.id_name);
        return rootView;
    }

    public void updataInfo(String name) {
        tv.setText("Wel Come " + name);
    }

}
