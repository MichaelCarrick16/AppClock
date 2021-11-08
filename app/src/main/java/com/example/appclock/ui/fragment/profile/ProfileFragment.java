package com.example.appclock.ui.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appclock.R;
import com.example.appclock.datasource.model.AccountModel;
import com.example.appclock.utils.app.App;

public class ProfileFragment extends Fragment {
    private View view;
    private ProfileViewModel profileViewModel;
    private EditText edtNameProfile , edtUsernameProfile , edtPasswordProfile , edtAddressProfile , edtPhoneProfile ;
    private TextView tvSaveProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_profile,container,false);
        initViews();
        return view;
    }

    private void initViews() {
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        edtNameProfile = view.findViewById(R.id.edt_fullname_profile);
        edtUsernameProfile = view.findViewById(R.id.edt_username_profile);
        edtPasswordProfile = view.findViewById(R.id.edt_password_profile);
        edtPhoneProfile = view.findViewById(R.id.edt_phone_profile);
        edtAddressProfile = view.findViewById(R.id.edt_address_profile);
        tvSaveProfile = view.findViewById(R.id.tv_save_profile);



        // show data of Account Login
        showData();

        // LiveData Observer
        profileViewModel.getAccount().observe(getViewLifecycleOwner(), new Observer<AccountModel>() {
            @Override
            public void onChanged(AccountModel accountModel) {
                edtNameProfile.setText(accountModel.getName());
                edtUsernameProfile.setText(accountModel.getUsername());
                edtAddressProfile.setText(accountModel.getAddress());
                edtPhoneProfile.setText(accountModel.getPhonenumber());
                edtPasswordProfile.setText(accountModel.getPassword());
            }
        });

    }

    private void showData() {
        String username = App.getInstance().getCheckIdLogin();
        profileViewModel.getApiAccount(username);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAccount();
            }
        });


    }

    private void updateAccount() {
        String name = edtNameProfile.getText().toString().trim();
        String username = edtUsernameProfile.getText().toString().trim();
        String address = edtAddressProfile.getText().toString().trim();
        String phone = edtPhoneProfile.getText().toString().trim();
        String password = edtPasswordProfile.getText().toString().trim();
        AccountModel accountModel = new AccountModel(username,password,address,phone,name);

        String usernameCurrent = App.getInstance().getCheckIdLogin();

        profileViewModel.updateApiAccount(usernameCurrent,accountModel,requireActivity());
    }
}
