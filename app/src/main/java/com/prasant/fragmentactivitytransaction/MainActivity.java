package com.prasant.fragmentactivitytransaction;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.prasant.fragmentactivitytransaction.fragment.AdminFragment;
import com.prasant.fragmentactivitytransaction.fragment.DemoFragment;
import com.prasant.fragmentactivitytransaction.fragment.DetailsFragment;
import com.prasant.fragmentactivitytransaction.fragment.DisplayFragment;
import com.prasant.fragmentactivitytransaction.fragment.UserFragment;
import com.prasant.fragmentactivitytransaction.interfacefile.FragmentCommunicate;

public class MainActivity extends AppCompatActivity implements DemoFragment.OnColoreChange, UserFragment.NameChangeListen, FragmentCommunicate, View.OnClickListener {
    private AdminFragment adminFragment;
    private DetailsFragment detailsFragment;
    private UserFragment userFragment;
    private DemoFragment demoFragment;
    private Button btnAdmin, btnUser, btnDetails, btnDemo;
    RelativeLayout relativeLayout;
    FragmentCommunicate fragmentCommunicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnUser = (Button) findViewById(R.id.btnUser);
        btnDetails = (Button) findViewById(R.id.btnDetails);
        btnDemo = (Button) findViewById(R.id.btnDemo);

        fragmentCommunicate = (FragmentCommunicate) this;

        btnAdmin.setOnClickListener(this);
        btnUser.setOnClickListener(this);
        btnDetails.setOnClickListener(this);
        btnDemo.setOnClickListener(this);

        adminFragment = new AdminFragment();
        detailsFragment = new DetailsFragment();
        demoFragment = new DemoFragment();
        userFragment = new UserFragment();
        this.getFragmentManager().beginTransaction().add(R.id.main_container, detailsFragment, "").commit();
    }

    @Override
    public void colorChanged(String color) {

        if (color.equals("RED")) {
            relativeLayout.setBackgroundColor(Color.RED);
        } else if (color.equals("GREEN")) {
            relativeLayout.setBackgroundColor(Color.GREEN);
        } else if (color.equals("BLUE")) {
            relativeLayout.setBackgroundColor(Color.BLUE);
        } else if (color.equals("YELLOW")) {
            relativeLayout.setBackgroundColor(Color.YELLOW);
        }

    }

    @Override
    public void naameDisplay(String name) {
        DisplayFragment adminFragment = (DisplayFragment) getFragmentManager().findFragmentById(R.id.fragmentDisplay);
        adminFragment.updataInfo(name);
    }

    @Override
    public void displayFrag(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.main_container, fragment, "").commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdmin:
                fragmentCommunicate.displayFrag(adminFragment);
                break;

            case R.id.btnUser:
                fragmentCommunicate.displayFrag(userFragment);
                break;
            case R.id.btnDetails:
                fragmentCommunicate.displayFrag(detailsFragment);

                break;
            case R.id.btnDemo:
                fragmentCommunicate.displayFrag(demoFragment);

                break;
        }


    }
}
