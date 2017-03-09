package com.example.pccuong.appbook.View.Login.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pccuong.appbook.model.login_registor.LoginBook;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.HomePage.HomePageActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

/**
 * Created by PCCuong on 12/31/2016.
 */

public class FragmantLogin extends Fragment implements View.OnClickListener {
    Button btnLoginFacebook, btnLoginGoogle, btnLogin;
    CallbackManager callbackManager;
    LoginBook loginBook;
    EditText edTenDangNhap,edMatKhau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmant_login, container, false);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), HomePageActivity.class);
                startActivity(iTrangChu);
                Log.d("kiemtra", " thanh cong");
            }

            @Override
            public void onCancel() {
                Log.d("kiemtra", " thoat");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("kiemtra", " Erro");
            }
        });
        loginBook = new LoginBook();
        edTenDangNhap = (EditText) view.findViewById(R.id.tendangnhap);
        edMatKhau = (EditText) view.findViewById(R.id.matkhau);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLoginFacebook = (Button) view.findViewById(R.id.btnLoginFacebook);
        btnLoginGoogle = (Button) view.findViewById(R.id.btnloginGoogle);
        btnLoginFacebook.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnLoginFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmantLogin.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnLogin:
                String dangNhap = edTenDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                boolean kiemtra = loginBook.kiemTraLogin(getActivity(), dangNhap, matkhau);
                if (kiemtra) {
                    Intent iTrangChu = new Intent(getActivity(), HomePageActivity.class);
                    startActivity(iTrangChu);
                } else {
                    Log.d("login","that bai" );
                    Toast.makeText(getActivity(), "Dang Nhap Ko Thanh Cong", Toast.LENGTH_SHORT);
                }
                break;
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
