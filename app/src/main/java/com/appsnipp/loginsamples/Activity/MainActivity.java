package com.appsnipp.loginsamples.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.appsnipp.loginsamples.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container_fragment, new com.appsnipp.loginsamples.Activity.MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        String names = GetSessionUser();

        if (menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new com.appsnipp.loginsamples.Activity.MainFragment());
            fragmentTransaction.commit();
        }
//        if(names.equals("")){
//            MenuItem item=(MenuItem)findViewById(R.id.profile);
//            item.setVisible(false);
//        }
        if (menuItem.getItemId() == R.id.profile){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new com.appsnipp.loginsamples.Activity.ProfileActivity());
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.login){
            Login();
        }
        if (menuItem.getItemId() == R.id.ssd){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new SSDFragment());
            fragmentTransaction.commit();
        }

        if (menuItem.getItemId() == R.id.hdd){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new HDDFragment());
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.logout){

            Logout();
        }

        return true;
    }



    public void Login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public  String  GetSessionUser(){

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }
    public void  Logout(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        SharedPreferences sharedPreferences = getSharedPreferences("user_check", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor =sharedPreferences.edit();
        editor.remove("user_email");
        editor.apply();
        finish();
    }
}