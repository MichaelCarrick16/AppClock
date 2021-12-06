package com.example.appclock.ui.act.allproductorder;

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
import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.ui.act.cartdetail.OnActionCallbackItemCartDetail;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    private Context context;
    private List<CartDetailModel> listCartDetail;


    public AllProductAdapter(Context context, List<CartDetailModel> listCartDetail) {
        this.context = context;
        this.listCartDetail = listCartDetail;
    }

    public void setDataNew(List<CartDetailModel> listCartDetail){
        this.listCartDetail = listCartDetail;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_allproduct,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CartDetailModel cartDetail = listCartDetail.get(position);
            Glide.with(context).load(cartDetail.getPictureProduct()).centerCrop().into(holder.imvLogoCart);
            holder.tvNameCart.setText(cartDetail.getNameProduct());
            holder.tvAmountCart.setText("Amount : "+cartDetail.getAmountProduct());
            holder.tvPriceCart.setText("Price : "+cartDetail.getPriceProduct()+"$");

    }

    @Override
    public int getItemCount() {
        return listCartDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvLogoCart = itemView.findViewById(R.id.imv_logo_all_product);
        TextView tvNameCart = itemView.findViewById(R.id.tv_name_all_product);
        TextView tvAmountCart = itemView.findViewById(R.id.tv_amount_all_product);
        TextView tvPriceCart = itemView.findViewById(R.id.tv_price_all_product);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
