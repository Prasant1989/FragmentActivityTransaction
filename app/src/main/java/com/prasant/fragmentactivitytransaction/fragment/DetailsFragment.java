package com.prasant.fragmentactivitytransaction.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prasant.fragmentactivitytransaction.MainActivity;
import com.prasant.fragmentactivitytransaction.R;
import com.prasant.fragmentactivitytransaction.adapter.UserAdapter;
import com.prasant.fragmentactivitytransaction.data.MyDBHelper;
import com.prasant.fragmentactivitytransaction.interfacefile.DeleteUser;
import com.prasant.fragmentactivitytransaction.model.StudentModel;

import java.util.List;

/**
 * A simple
 * {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements DeleteUser {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    public MyDBHelper myDataBase;
    UserAdapter mAdapter;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        myDataBase = new MyDBHelper(getActivity());
        List<StudentModel> allProducts = myDataBase.getAllUsers();
        if (allProducts.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
             mAdapter = new UserAdapter(getActivity(), allProducts);
            recyclerView.setAdapter(mAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "There is no product in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });

        return rootView;
    }

    private void displayAlertDialog() {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.add_alertdialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptsView);

        final EditText userName = (EditText) promptsView.findViewById(R.id.edtName);
        final EditText userEmail = (EditText) promptsView.findViewById(R.id.edtemail);
        final EditText userPhone = (EditText) promptsView.findViewById(R.id.edtPhone);
        alertDialogBuilder.setTitle("Add Details");
        alertDialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton) {
                String name = userName.getText().toString().trim();
                String email = userEmail.getText().toString().trim();
                String phone = userPhone.getText().toString().trim();

                if (name.isEmpty()) {
                    userName.setError("Enter Name");
                    userName.setFocusable(true);
                } else if (email.isEmpty()) {
                    userEmail.setError("Enter Email");
                    userEmail.setFocusable(true);

                } else if (phone.isEmpty()) {
                    userPhone.setError("Enter Phone");
                    userPhone.setFocusable(true);
                } else if (userName != null && userEmail != null && userPhone != null) {
                    long isInserted = myDataBase.addUserDetail(name, email, phone);
                    if (isInserted > 0)
                        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();
                }

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                alertDialogBuilder.setCancelable(true);
            }
        });
        AlertDialog b = alertDialogBuilder.create();
        b.show();
    }

    @Override
    public void deleteUser(int std_id)
    {
        myDataBase.deleteUSer(std_id);
        mAdapter.notifyDataSetChanged();

    }
}
