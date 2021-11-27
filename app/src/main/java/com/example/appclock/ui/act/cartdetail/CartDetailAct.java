package com.example.appclock.ui.act.cartdetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appclock.R;
import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.ui.act.allproductorder.AllProductOrder;
import com.example.appclock.ui.act.cartdetail.adapter.CartAdapter;
import com.example.appclock.ui.fragment.home.HomeViewModel;
import com.example.appclock.utils.app.App;
import com.example.appclock.utils.storage.Storage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDetailAct extends AppCompatActivity implements OnActionCallbackItemCartDetail {
    private ImageView imvBackCart;
    private TextView tvOrderCart;
    private RecyclerView recyclerViewOrderCart;
    private CartAdapter cartAdapter;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cart_detail);
        initViews();
        initEvents();
        showListCartDetail();

    }

    private void showListCartDetail() {
        List<CartDetailModel> listCartDetail = Storage.getStorage().getListCartDetail();

        cartAdapter = new CartAdapter(this,listCartDetail);
        cartAdapter.setOnCallback(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewOrderCart.setLayoutManager(layoutManager);
        recyclerViewOrderCart.setAdapter(cartAdapter);

    }

    private void initEvents() {
        imvBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvOrderCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Storage.getStorage().getListCartDetail().size()==0){
                    Toast.makeText(CartDetailAct.this,"Order Fail",Toast.LENGTH_SHORT).show();
                    App.getInstance().setCheckButtonOrder(true);
                    return;
                }
                Storage.getStorage().setCheckOrder(1);
                App.getInstance().setCheckButtonOrder(false);

                SingletonRetrofit.getInstance().postListCartDetail(Storage.getStorage().getListCartDetail()).enqueue(new Callback<List<CartDetailModel>>() {
                    @Override
                    public void onResponse(Call<List<CartDetailModel>> call, Response<List<CartDetailModel>> response) {
                    }

                    @Override
                    public void onFailure(Call<List<CartDetailModel>> call, Throwable t) {
                    }
                });

                Toast.makeText(CartDetailAct.this,"Order Success",Toast.LENGTH_SHORT).show();

                Storage.getStorage().setListCartDetail(new ArrayList<CartDetailModel>());
                cartAdapter.setDataNew(Storage.getStorage().getListCartDetail());
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartDetailAct.this, AllProductOrder.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        imvBackCart = findViewById(R.id.imv_back_cart);
        tvOrderCart = findViewById(R.id.tv_order_cart);
        recyclerViewOrderCart = findViewById(R.id.recycler_view_order_cart);
        floatingActionButton = findViewById(R.id.floating_action_button);
    }

    @Override
    public void onCallback(CartDetailModel cartDetailModel) {
        Storage.getStorage().setCheckOrder(2);

        Storage.getStorage().getListCartDetail().remove(cartDetailModel);
        cartAdapter.setDataNew(Storage.getStorage().getListCartDetail());

        Storage.getStorage().setListCartDetail(Storage.getStorage().getListCartDetail());
    }
}
