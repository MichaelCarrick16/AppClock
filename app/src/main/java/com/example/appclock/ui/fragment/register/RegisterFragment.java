package com.example.appclock.ui.fragment.register;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appclock.R;

public class RegisterFragment extends Fragment {
    private View view;
    private EditText edtUsernameRegister , edtPhoneRegister , edtAddressRegister , edtPasswordRegister , edtConfirmPasswordRegister ;
    private TextView tvCancelRegister , tvRegisterRegister ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_register,container,false);
        initViews();
        return view;
    }

    private void initViews() {
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
    }
}
