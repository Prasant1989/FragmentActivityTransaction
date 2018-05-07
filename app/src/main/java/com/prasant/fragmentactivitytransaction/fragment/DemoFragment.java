package com.prasant.fragmentactivitytransaction.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.prasant.fragmentactivitytransaction.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {

    RadioGroup color_group;
    OnColoreChange onColoreChange;


    public DemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
        color_group = (RadioGroup) rootView.findViewById(R.id.color_group);
        color_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.id_red:
                        onColoreChange.colorChanged("RED");
                        break;
                    case R.id.id_green:
                        onColoreChange.colorChanged("GREEN");
                        break;
                    case R.id.id_pink:
                        onColoreChange.colorChanged("BLUE");
                        break;
                    case R.id.id_yellow:
                        onColoreChange.colorChanged("YELLOW");
                        break;
                }

            }
        });


        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onColoreChange=(OnColoreChange)activity;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public interface OnColoreChange{
        public void colorChanged(String color);
    }

}
