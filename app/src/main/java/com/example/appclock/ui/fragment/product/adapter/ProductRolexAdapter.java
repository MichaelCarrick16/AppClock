package com.example.appclock.ui.fragment.product.adapter;

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

public class ProductRolexAdapter extends RecyclerView.Adapter<ProductRolexAdapter.ViewHolder> {
    private List<ProductModel> listProductRolex;
    private Context context;
    private OnActionCallbackHomeAdapterToHomeFragment callBack;

    public ProductRolexAdapter(List<ProductModel> listProductRolex, Context context) {
        this.listProductRolex = listProductRolex;
        this.context = context;
    }

    public void setCallBack(OnActionCallbackHomeAdapterToHomeFragment callBack) {
        this.callBack = callBack;
    }

    public void setNewData(List<ProductModel> list){
        listProductRolex = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel productModel = listProductRolex.get(position);
        Glide.with(context).load(productModel.getImageProduct()).centerCrop().into(holder.imvLogoItemProduct);
        holder.tvNameItemProduct.setText(productModel.getNameProduct());
        holder.tvPriceItemProduct.setText(String.valueOf(productModel.getPriceProduct())+"$");
        holder.constraintLayoutItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onCallback(productModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProductRolex.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvLogoItemProduct = itemView.findViewById(R.id.imv_logo_item_product);
        TextView tvNameItemProduct = itemView.findViewById(R.id.tv_name_item_product);
        TextView tvPriceItemProduct = itemView.findViewById(R.id.tv_price_item_product);
        ConstraintLayout constraintLayoutItemProduct = itemView.findViewById(R.id.constraint_layout_item_product);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
