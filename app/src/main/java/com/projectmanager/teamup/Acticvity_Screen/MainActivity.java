package com.projectmanager.teamup.Acticvity_Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.projectmanager.teamup.Fragments_Screen.CreateProjectFragment;
import com.projectmanager.teamup.Fragments_Screen.HomePageFragment;
import com.projectmanager.teamup.Fragments_Screen.ProfilePageFragment;
import com.projectmanager.teamup.Fragments_Screen.SearchFragment;
import com.projectmanager.teamup.Fragments_Screen.SettingPageFragment;
import com.projectmanager.teamup.R;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
  private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navibottom);
        mAuth = FirebaseAuth.getInstance();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.home){
                    loadFragment(new HomePageFragment(),true);
                }else if(id == R.id.CreateProject){
                    loadFragment(new CreateProjectFragment(),false);
                }else if(id == R.id.setting){
                    loadFragment(new SettingPageFragment(),false);
                }
                else {
                    loadFragment(new ProfilePageFragment(),false);
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);


    }

    public void loadFragment(Fragment fragment, boolean flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        if(flag==true){
            ft.add(R.id.container,fragment);
        }
        else {
            ft.replace(R.id.container,fragment);
        }
        ft.commit();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // adding a click listener for option selected on below line.
        int id = item.getItemId();
        switch (id) {
            case R.id.idLogOut:
                // displaying a toast message on user logged out inside on click.
                Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_LONG).show();
                // on below line we are signing out our user.

                mAuth.signOut();
                // on below line we are opening our login activity.
                Intent i = new Intent(MainActivity.this, Login_Screen.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // on below line we are inflating our menu
        // file for displaying our menu options.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }


}