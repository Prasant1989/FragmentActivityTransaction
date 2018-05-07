package com.prasant.fragmentactivitytransaction.fragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.prasant.fragmentactivitytransaction.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    EditText editText;
    Button button;
    String name;
    NameChangeListen nameChangeListen;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        editText = (EditText) rootView.findViewById(R.id.edt_id);
        button = (Button) rootView.findViewById(R.id.btn_sumit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString().trim();
                if (name.isEmpty()) {
                    editText.setFocusable(true);
                    editText.setError("Please Enter Name");
                } else if (!name.isEmpty()) {
                    nameChangeListen.naameDisplay(name);
                }
            }
        });
        return rootView;
    }

    public interface NameChangeListen {
        public void naameDisplay(String name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            nameChangeListen = (NameChangeListen) context;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
