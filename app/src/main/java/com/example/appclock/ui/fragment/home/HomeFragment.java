package com.example.appclock.ui.fragment.home;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appclock.R;
import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.ProductModel;
import com.example.appclock.datasource.model.TrademarkModel;
import com.example.appclock.ui.fragment.home.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnActionCallbackHomeAdapterToHomeFragment {
    private View view;
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewHome;
    private HomeAdapter homeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home,container,false);
        initViews();
        return view;
    }

    private void initViews() {
       homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

       recyclerViewHome = view.findViewById(R.id.recycler_view_home);

       homeAdapter = new HomeAdapter(homeViewModel.listProductDefault,getContext());
       homeAdapter.setCallback(this);
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
       recyclerViewHome.setLayoutManager(gridLayoutManager);
       recyclerViewHome.setAdapter(homeAdapter);

       homeViewModel.getListProductNewHome().observe(getViewLifecycleOwner(), new Observer<List<ProductModel>>() {
           @Override
           public void onChanged(List<ProductModel> productModels) {
                homeAdapter.setNewData(productModels);
           }
       });



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCallback(ProductModel productModel) {
        Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_item_home);

        ImageView imvBackDialog = dialog.findViewById(R.id.imv_back_dialog);
        ImageView imvLogoDialog = dialog.findViewById(R.id.imv_logo_dialog);
        TextView tvNameDialog = dialog.findViewById(R.id.tv_name_dialog);
        TextView tvPriceDialog = dialog.findViewById(R.id.tv_price_dialog);
        EditText edtNumberDialog = dialog.findViewById(R.id.edt_number_dialog);
        TextView tvPurchaseDialog = dialog.findViewById(R.id.tv_purchase_dialog);
        TextView tvDescription = dialog.findViewById(R.id.tv_description_dialog);


        Glide.with(getContext()).load(productModel.getImageProduct()).centerCrop().into(imvLogoDialog);
        tvNameDialog.setText(productModel.getNameProduct());
        tvPriceDialog.setText(String.valueOf("Price : "+productModel.getPriceProduct())+"$");
        tvDescription.setText(productModel.getDescriptionProduct());

        imvBackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
