package com.example.appclock.ui.act.cartdetail.adapter;

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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<CartDetailModel> listCartDetail;
    private OnActionCallbackItemCartDetail onCallback;

    public CartAdapter(Context context, List<CartDetailModel> listCartDetail) {
        this.context = context;
        this.listCartDetail = listCartDetail;
    }

    public void setDataNew(List<CartDetailModel> listCartDetail){
        this.listCartDetail = listCartDetail;
        notifyDataSetChanged();
    }

    public void setOnCallback(OnActionCallbackItemCartDetail onCallback) {
        this.onCallback = onCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CartDetailModel cartDetail = listCartDetail.get(position);
            Glide.with(context).load(cartDetail.getPictureProduct()).centerCrop().into(holder.imvLogoCart);
            holder.tvNameCart.setText(cartDetail.getNameProduct());
            holder.tvAmountCart.setText("Amount : "+cartDetail.getAmountProduct());
            holder.tvPriceCart.setText("Price : "+cartDetail.getPriceProduct()+"$");
            holder.constraintLayoutItemCart.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onCallback.onCallback(cartDetail);
                    return false;
                }
            });
    }

    @Override
    public int getItemCount() {
        return listCartDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvLogoCart = itemView.findViewById(R.id.imv_logo_cart);
        TextView tvNameCart = itemView.findViewById(R.id.tv_name_cart);
        TextView tvAmountCart = itemView.findViewById(R.id.tv_amount_cart);
        TextView tvPriceCart = itemView.findViewById(R.id.tv_price_cart);
        ConstraintLayout constraintLayoutItemCart = itemView.findViewById(R.id.constraint_layout_item_cart);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
