package com.example.appclock.ui.fragment.login;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.appclock.ui.act.main.MainAct;
import com.example.appclock.utils.app.App;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class LoginFragment extends Fragment {
    private static final String USER_NAME = "USER_NAME";
    private static final String PASS_WORD = "PASS_WORD";
    private static final String CHECK_SAVE = "CHECK_SAVE";
    private View view;

    private static final int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;

    private TextView tvCreateLogin , tvLoginLogin;
    private EditText edtUsernameLogin , edtPasswordLogin;
    private ImageView imvShowPasswordLogin , imvSaveLogin;
    private LoginAndRegisterViewModel loginAndRegisterViewModel;

    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;

    // listDefault để lấy data trong LiveData bắn về khi Login
    private List<AccountModel> listDefault;

    // Check xem showPassword dang o trang thai nao (mac dinh la dang an password di)
    private Boolean checkShowPassword = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_login,container,false);
        initViews();
        return view;
    }

    private void initViews() {
        loginAndRegisterViewModel = new ViewModelProvider(requireActivity()).get(LoginAndRegisterViewModel.class);
        signInButton = view.findViewById(R.id.sign_in_button);
        tvCreateLogin = view.findViewById(R.id.tv_create_login);
        tvLoginLogin = view.findViewById(R.id.tv_login_login);
        edtUsernameLogin = view.findViewById(R.id.edt_username_login);
        edtPasswordLogin = view.findViewById(R.id.edt_password_login);
        imvShowPasswordLogin = view.findViewById(R.id.imv_show_password_login);
        imvSaveLogin = view.findViewById(R.id.imv_save_login);
        sharedPreferences = requireActivity().getSharedPreferences("Pref_Save_Login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        handleGmail();
//        showListAccountByLiveData();
        saveAccount();


    }

    private void saveAccount() {
        edtUsernameLogin.setText(sharedPreferences.getString(USER_NAME,""));
        edtPasswordLogin.setText(sharedPreferences.getString(PASS_WORD,""));
        Boolean check = sharedPreferences.getBoolean(CHECK_SAVE,false);
        if(check){
            imvSaveLogin.setImageResource(R.drawable.ic_checkbox_login);
        }else{
            imvSaveLogin.setImageResource(R.drawable.ic_uncheckbox_login);
        }
    }

    private void showListAccountByLiveData() {
        loginAndRegisterViewModel.getApiAccount();

        loginAndRegisterViewModel.getListAccount().observe(getViewLifecycleOwner(), new Observer<List<AccountModel>>() {
            @Override
            public void onChanged(List<AccountModel> accountModels) {
               listDefault = accountModels;
               Log.e("abc",accountModels.size()+"");
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        // handle Sign In Gmail
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               signIn();
            }
        });

        // handle click create account
        tvCreateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
                clearEdittext();
            }
        });

        // handle click login account
        tvLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getInstance().setCheckLoginAccountorGmail(false);
                Intent intent = new Intent(getContext(),MainAct.class);
                startActivity(intent);
//                checkLogin();
            }
        });

        // update length password de handle Visible of showPassword
        edtPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int length = charSequence.toString().length();
                    if(length==0){
                        imvShowPasswordLogin.setVisibility(View.GONE);
                    }else{
                        imvShowPasswordLogin.setVisibility(View.VISIBLE);
                    }
            }
        });

        // handle click showPassword
        imvShowPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkShowPassword==false){
                    checkShowPassword = true;
                    edtPasswordLogin.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    checkShowPassword = false;
                    edtPasswordLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        // handle Save Account
        imvSaveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = sharedPreferences.getBoolean(CHECK_SAVE,false);
                if(check){
                    editor.putBoolean(CHECK_SAVE,false);
                    editor.putString(USER_NAME,"");
                    editor.putString(PASS_WORD,"");
                    editor.apply();
                    imvSaveLogin.setImageResource(R.drawable.ic_uncheckbox_login);
                }else {
                    editor.putBoolean(CHECK_SAVE,true);
                    editor.putString(USER_NAME,edtUsernameLogin.getText().toString().trim());
                    editor.putString(PASS_WORD,edtPasswordLogin.getText().toString().trim());
                    editor.apply();
                    imvSaveLogin.setImageResource(R.drawable.ic_checkbox_login);
                }
            }
        });


    }

    private void checkLogin() {
        boolean resultCheckLogin = false;
        String username = edtUsernameLogin.getText().toString().trim();
        String password = edtPasswordLogin.getText().toString().trim();
        for(AccountModel accountModel : listDefault){
            if(accountModel.getUsername().equals(username)&&accountModel.getPassword().equals(password)){
                resultCheckLogin = true;
            }
        }
        if(resultCheckLogin){
            App.getInstance().setCheckLoginAccountorGmail(false);
            Intent intent = new Intent(getContext(),MainAct.class);
            startActivity(intent);
            if(sharedPreferences.getBoolean(CHECK_SAVE,false)==false){
                clearEdittext();
            }
        }else{
            Toast.makeText(getContext(),"Sai cmnr còn đâu =))",Toast.LENGTH_SHORT).show();
        }
    }

    private void clearEdittext() {
        edtPasswordLogin.setText("");
        edtUsernameLogin.setText("");
    }

    // Handle Gmail
    private void handleGmail() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Success
            App.getInstance().setCheckLoginAccountorGmail(true);
            Intent intent = new Intent(getContext(), MainAct.class);
            startActivity(intent);
        } catch (ApiException e) {
            //Fail
            Toast.makeText(getContext(),"Fail",Toast.LENGTH_SHORT).show();
        }
    }



}
