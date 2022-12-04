package com.projectmanager.teamup.Fragments_Screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projectmanager.teamup.Activity_Screen.Login_Screen;
import com.projectmanager.teamup.Activity_Screen.MainActivity;
import com.projectmanager.teamup.R;


public class ProfilePageFragment extends Fragment {

    private Button Logout, MapOffice;
    private FirebaseAuth mAuth;
    private ImageView DP;
    private TextView MyEmail;
//    String Email;

    public ProfilePageFragment() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);
        DP = view.findViewById(R.id.picId);
        Logout = view.findViewById(R.id.LogoutBtn);
        MyEmail = view.findViewById(R.id.CatchEmail);
        mAuth = FirebaseAuth.getInstance();
        MapOffice = view.findViewById(R.id.mapIdBtn);
        MapOffice.setText("Office Location");
        MapOffice.setTextColor(Color.BLACK);
        Logout.setText("Logout");
        Logout.setTextColor(Color.BLACK);
        MapOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Office.class));
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
            }
        });

//        Bundle bundle = getArguments();

        DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new BlankFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.container, f).commit();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            String Email;
            Email = firebaseUser.getEmail();
            MyEmail.setText(Email);
            Log.d("Profile: Email: ", Email);


        } else {
            getFragmentManager().beginTransaction().detach(ProfilePageFragment.this).attach(ProfilePageFragment.this).commit();
            Toast.makeText(getContext(), "Logout Successfully...!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), Login_Screen.class));
            mAuth.signOut();

        }


    }


}