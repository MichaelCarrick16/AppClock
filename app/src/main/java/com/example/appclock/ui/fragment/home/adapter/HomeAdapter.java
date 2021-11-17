package com.example.appclock.ui.fragment.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appclock.R;
import com.example.appclock.datasource.model.ProductModel;
import com.example.appclock.ui.fragment.home.OnActionCallbackHomeAdapterToHomeFragment;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<ProductModel> listProduct;
    private Context context;
    private OnActionCallbackHomeAdapterToHomeFragment callback;

    public HomeAdapter(List<ProductModel> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }

    public void setCallback(OnActionCallbackHomeAdapterToHomeFragment callback) {
        this.callback = callback;
    }

    public void setNewData(List<ProductModel> listProduct){
        this.listProduct = listProduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ProductModel productModel = listProduct.get(position);
            Glide.with(context).load(productModel.getImageProduct()).centerCrop().into(holder.imvLogoItemHome);
            holder.tvNameItemHome.setText(productModel.getNameProduct());
            holder.tvPriceItemHome.setText(String.valueOf(productModel.getPriceProduct())+"$");
            holder.constraintLayoutItemHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onCallback(productModel);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvLogoItemHome = itemView.findViewById(R.id.imv_logo_item_home);
        TextView tvNameItemHome = itemView.findViewById(R.id.tv_name_item_home);
        TextView tvPriceItemHome = itemView.findViewById(R.id.tv_price_item_home);
        ConstraintLayout constraintLayoutItemHome = itemView.findViewById(R.id.constraint_layout_item_home);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
