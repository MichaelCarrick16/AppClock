package com.example.appclock.ui.fragment.product;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appclock.R;
import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.datasource.model.ProductModel;
import com.example.appclock.ui.fragment.home.HomeViewModel;
import com.example.appclock.ui.fragment.home.OnActionCallbackHomeAdapterToHomeFragment;
import com.example.appclock.ui.fragment.product.adapter.ProductRolexAdapter;
import com.example.appclock.utils.app.App;
import com.example.appclock.utils.storage.Storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProductFragment extends Fragment implements OnActionCallbackHomeAdapterToHomeFragment {
    private View view;
    private HomeViewModel homeViewModel;
    private TextView tvRolexProduct , tvHublotProduct , tvPiagetProduct;
    private RecyclerView recyclerViewRolexProduct , recyclerViewHublotProduct , recyclerViewPiagetProduct;
    private ProductRolexAdapter productRolexAdapter , productHublotAdapter , productPiagetAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_product,container,false);
        initViews();
        return view;
    }

    private void initViews() {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        tvRolexProduct = view.findViewById(R.id.tv_rolex_product);
        tvHublotProduct = view.findViewById(R.id.tv_hublot_product);
        tvPiagetProduct = view.findViewById(R.id.tv_piaget_product);

        recyclerViewRolexProduct = view.findViewById(R.id.recycler_view_rolex_product);
        recyclerViewHublotProduct = view.findViewById(R.id.recycler_view_hublot_product);
        recyclerViewPiagetProduct = view.findViewById(R.id.recycler_view_piaget_product);

        productRolexAdapter = new ProductRolexAdapter(homeViewModel.getListRolex(),getContext());
        productRolexAdapter.setCallBack(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewRolexProduct.setLayoutManager(gridLayoutManager);
        recyclerViewRolexProduct.setAdapter(productRolexAdapter);

        productHublotAdapter = new ProductRolexAdapter(homeViewModel.getListHublot(),getContext());
        productHublotAdapter.setCallBack(this);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewHublotProduct.setLayoutManager(gridLayoutManager);
        recyclerViewHublotProduct.setAdapter(productHublotAdapter);

        productPiagetAdapter = new ProductRolexAdapter(homeViewModel.getListPiaget(),getContext());
        productPiagetAdapter.setCallBack(this);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewPiagetProduct.setLayoutManager(gridLayoutManager);
        recyclerViewPiagetProduct.setAdapter(productPiagetAdapter);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showTrademark();
    }

    private void showTrademark() {
        tvRolexProduct.setText(homeViewModel.getListTradeMark().get(0).getName());
        tvHublotProduct.setText(homeViewModel.getListTradeMark().get(1).getName());
        tvPiagetProduct.setText(homeViewModel.getListTradeMark().get(2).getName());
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

        String idCart = App.getInstance().getCartLogin().getId();
        int idProduct = productModel.getIdProduct();
        String nameProduct = productModel.getNameProduct();
        String pictureProduct = productModel.getImageProduct();
        int priceProduct = productModel.getPriceProduct();


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

        tvPurchaseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(edtNumberDialog.getText().toString().trim());
                String idCartDetailByTimeCurrent = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());

                CartDetailModel cartDetailModel = new CartDetailModel(idCartDetailByTimeCurrent,idCart,idProduct,nameProduct,pictureProduct,number,priceProduct);
                homeViewModel.listCartDetail.add(cartDetailModel);
                homeViewModel.getListCartDetailUpdate().setValue(homeViewModel.listCartDetail);
                Storage.getStorage().setListCartDetail(homeViewModel.listCartDetail);
                Toast.makeText(getContext(),"Done!",Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();
    }
}
