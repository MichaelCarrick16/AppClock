package com.example.appclock.ui.fragment.login;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.appclock.R;
import com.example.appclock.ui.act.main.MainAct;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginFragment extends Fragment {
    private View view;

    private static final int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;

    private TextView tvCreateLogin , tvLoginLogin;
    private EditText edtUsernameLogin , edtPasswordLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_login,container,false);
        initViews();
        return view;
    }

    private void initViews() {
        signInButton = view.findViewById(R.id.sign_in_button);
        tvCreateLogin = view.findViewById(R.id.tv_create_login);
        tvLoginLogin = view.findViewById(R.id.tv_login_login);
        edtUsernameLogin = view.findViewById(R.id.edt_username_login);
        edtPasswordLogin = view.findViewById(R.id.edt_password_login);
        handleGmail();

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
            }
        });

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
            Intent intent = new Intent(getContext(), MainAct.class);
            startActivity(intent);
        } catch (ApiException e) {
            //Fail
            Toast.makeText(getContext(),"Fail",Toast.LENGTH_SHORT).show();
        }
    }



}
