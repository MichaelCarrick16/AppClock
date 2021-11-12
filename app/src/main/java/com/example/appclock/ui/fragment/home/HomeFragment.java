package com.example.appclock.ui.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class HomeFragment extends Fragment {
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

       homeViewModel.getListProductNewHome().observe(getViewLifecycleOwner(), new Observer<List<ProductModel>>() {
           @Override
           public void onChanged(List<ProductModel> productModels) {
               homeAdapter = new HomeAdapter(productModels,getContext());
               GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
               recyclerViewHome.setLayoutManager(gridLayoutManager);
               recyclerViewHome.setAdapter(homeAdapter);
           }
       });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
