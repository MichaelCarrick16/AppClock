package com.example.appclock.ui.act.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appclock.R;
import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.datasource.model.CartModel;
import com.example.appclock.ui.act.cartdetail.CartDetailAct;
import com.example.appclock.ui.fragment.home.HomeViewModel;
import com.example.appclock.utils.app.App;
import com.example.appclock.utils.storage.Storage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAct extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ImageView imvMenuMain , imvCartMain , imvSearchMain;
    private TextView tvCountMain ;
    private EditText edtSearchMain;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private HomeViewModel homeViewModel;

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
        handleLogOutDrawer();
        // handle show cart detail
        showCartDetail();

        // Handle để bên Home không bị nhân bản data
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.callAPIProductNews();

        // Handle để load data luôn cho Product
        homeViewModel.callAPITrademark();

        // Handle de load data theo tung trademark
        homeViewModel.callAPIListProductByTrademark();

        homeViewModel.getListCartDetailUpdate().observe(this, new Observer<List<CartDetailModel>>() {
            @Override
            public void onChanged(List<CartDetailModel> cartDetailModels) {
                tvCountMain.setText(cartDetailModels.size()+"");
            }
        });

        handleSearch();



    }

    private void handleSearch() {
        imvSearchMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage.getStorage().setTextSearch(edtSearchMain.getText().toString().trim());
                Storage.getStorage().setListHublotDefault(new ArrayList<>());
                Storage.getStorage().setListRolexDefault(new ArrayList<>());
                Storage.getStorage().setListPiagetDefault(new ArrayList<>());
            }
        });
    }


    private void showCartDetail() {
        imvCartMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAct.this, CartDetailAct.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogOutDrawer() {
        navigationView.getMenu().findItem(R.id.logoutFragment).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(App.getInstance().getCheckButtonOrder()){
                    SingletonRetrofit.getInstance().deleteCart(App.getInstance().getCartLogin().getId()).enqueue(new Callback<CartModel>() {
                        @Override
                        public void onResponse(Call<CartModel> call, Response<CartModel> response) {

                        }

                        @Override
                        public void onFailure(Call<CartModel> call, Throwable t) {

                        }
                    });
                }
                Storage.getStorage().setListCartDetail(new ArrayList<CartDetailModel>());
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
        edtSearchMain = findViewById(R.id.edt_search_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        imvMenuMain = findViewById(R.id.imv_menu_main);
        imvCartMain = findViewById(R.id.imv_cart_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        tvCountMain = findViewById(R.id.tv_count_main);
        imvSearchMain = findViewById(R.id.imv_search_main);
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
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Toast.makeText(this,personEmail+"/"+personName+"/"+personId,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("abc","stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("abc","restart");
        if (Storage.getStorage().getCheckOrder()==1){
            homeViewModel.getListCartDetailUpdate().setValue(Storage.getStorage().getListCartDetail());
            homeViewModel.listCartDetail = new ArrayList<>();
            Storage.getStorage().setCheckOrder(0);
        }else if(Storage.getStorage().getCheckOrder()==2){
            homeViewModel.getListCartDetailUpdate().setValue(Storage.getStorage().getListCartDetail());
            Storage.getStorage().setCheckOrder(0);
        }

    }
}