package com.example.appclock.ui.act.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appclock.R;
import com.example.appclock.utils.app.App;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainAct extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ImageView imvMenuMain;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initViews();

        // Result Gmail
        loginGmail();
        // handle BottomNavigation
        handleBottomNavigation();
        // handle open NavigationDrawer
        openNavigationDrawer();
        // handle NavigationDrawer
        handleNavigationDrawer();
        // handle logout
        handleLogoutDrawer();


    }

    private void handleLogoutDrawer() {
        navigationView.getMenu().findItem(R.id.logoutFragment).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                finish();
                return false;
            }
        });
    }

    private void handleNavigationDrawer() {
        navigationView.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_main);
        NavigationUI.setupWithNavController(navigationView,navController);
    }

    private void openNavigationDrawer() {
        imvMenuMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        imvMenuMain = findViewById(R.id.imv_menu_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
    }

    private void handleBottomNavigation() {
       NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_main);
       NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    private void loginGmail() {
        if(App.getInstance().getCheckLoginAccountorGmail()==true){
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Toast.makeText(this,personEmail+"/"+personName,Toast.LENGTH_LONG).show();
            }
        }
    }
}