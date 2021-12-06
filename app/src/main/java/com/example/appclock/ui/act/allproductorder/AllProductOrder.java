package com.example.appclock.ui.act.allproductorder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appclock.R;
import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.utils.app.App;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductOrder extends AppCompatActivity {
    private ImageView imvBackAllProductOrder;
    private RecyclerView recyclerViewAllProductOrder;
    private List<CartDetailModel> listCartDetail;
    private AllProductAdapter allProductAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_all_product_order);
        initViews();
        initEvents();
    }

    private void initEvents() {
        imvBackAllProductOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }

    private void initViews() {
        imvBackAllProductOrder = findViewById(R.id.imv_back_all_product_order);
        recyclerViewAllProductOrder = findViewById(R.id.recycler_view_all_product_order);
        listCartDetail = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(this,listCartDetail);
        recyclerViewAllProductOrder.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerViewAllProductOrder.setAdapter(allProductAdapter);


        SingletonRetrofit.getInstance().getListCartDetail(App.getInstance().getCartLogin().getId()).enqueue(new Callback<List<CartDetailModel>>() {
            @Override
            public void onResponse(Call<List<CartDetailModel>> call, Response<List<CartDetailModel>> response) {
                listCartDetail = response.body();
                allProductAdapter.setDataNew(listCartDetail);
            }

            @Override
            public void onFailure(Call<List<CartDetailModel>> call, Throwable t) {

            }
        });
    }
}
