package com.example.appclock.ui.fragment.register;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appclock.R;
import com.example.appclock.datasource.model.AccountModel;
import com.example.appclock.ui.fragment.login.LoginAndRegisterViewModel;

import java.util.List;

public class RegisterFragment extends Fragment {
    private View view;
    private EditText edtUsernameRegister , edtPhoneRegister , edtAddressRegister , edtPasswordRegister , edtConfirmPasswordRegister ;
    private TextView tvCancelRegister , tvRegisterRegister ;
    private LoginAndRegisterViewModel loginAndRegisterViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_register,container,false);
        initViews();
        return view;
    }

    private void initViews() {
        loginAndRegisterViewModel = new ViewModelProvider(requireActivity()).get(LoginAndRegisterViewModel.class);

        edtUsernameRegister = view.findViewById(R.id.edt_username_register);
        edtPhoneRegister = view.findViewById(R.id.edt_phone_register);
        edtAddressRegister = view.findViewById(R.id.edt_address_register);
        edtPasswordRegister = view.findViewById(R.id.edt_password_register);
        edtConfirmPasswordRegister = view.findViewById(R.id.edt_confirm_password_register);
        tvCancelRegister = view.findViewById(R.id.tv_cancel_register);
        tvRegisterRegister = view.findViewById(R.id.tv_register_register);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        tvCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        tvRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsernameRegister.getText().toString().trim();
                String password = edtPasswordRegister.getText().toString().trim();
                String confirmPassword = edtConfirmPasswordRegister.getText().toString().trim();
                String phone = edtPhoneRegister.getText().toString().trim();
                String address = edtAddressRegister.getText().toString().trim();
                if(username.equals("")||password.equals("")||confirmPassword.equals("")||phone.equals("")||address.equals("")){
                    Toast.makeText(getContext(),"Cần điền đủ thông tin vào nhé !",Toast.LENGTH_SHORT).show();
                }else if(checkUsername(username)){
                    Toast.makeText(getContext(),"Xin là xin vĩnh biệt cụ vì đã trùng Username",Toast.LENGTH_SHORT).show();
                }else if(phone.length()!=10){
                    Toast.makeText(getContext(),"Số điện thoại phải có 10 số",Toast.LENGTH_SHORT).show();
                }else if(confirmPassword.equals(password)==false){
                    Toast.makeText(getContext(),"Confirm Password chắc chắn sai :v",Toast.LENGTH_SHORT).show();
                }else{
                    AccountModel accountModel = new AccountModel(username,password,address,phone);
                    loginAndRegisterViewModel.postApiAccount(accountModel);
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    requireActivity().onBackPressed();
                }
            }
        });
    }

    private boolean checkUsername(String username) {
        for(int i=0;i<loginAndRegisterViewModel.list.size();i++){
            AccountModel accountModel = loginAndRegisterViewModel.list.get(i);
            if(accountModel.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
