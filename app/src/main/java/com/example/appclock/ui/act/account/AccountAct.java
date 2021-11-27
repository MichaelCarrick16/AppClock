package com.example.appclock.ui.act.account;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appclock.R;
import com.example.appclock.utils.app.App;
import com.google.android.material.navigation.NavigationView;

public class AccountAct extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_account);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Setup lại giá trị check Order bằng true
        App.getInstance().setCheckButtonOrder(true);
    }
}
