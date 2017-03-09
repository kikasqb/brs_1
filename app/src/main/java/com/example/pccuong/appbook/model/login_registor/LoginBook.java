package com.example.pccuong.appbook.model.login_registor;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 2/7/2017.
 */

public class LoginBook {
    boolean kiemtra = false;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken layTockenFacebookRuntime() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public void closeTacker() {
        accessTokenTracker.isTracking();
    }

    public String layCaheRerferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        return name;
    }

    public void upDataLogin(Context context, String name) {
        SharedPreferences caheSharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = caheSharedPreferences.edit();
        editor.putString("name", name);
        editor.commit();
    }


    public boolean kiemTraLogin(Context context, String email, String password) {

        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KiemTraDangNhap");
        HashMap<String, String> hsEmail = new HashMap<>();
        hsEmail.put("email", email);
        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("encrypted_password", password);
        attrs.add(hsHam);
        attrs.add(hsEmail);
        attrs.add(hsMatKhau);
        DowloadJSON dowloadJSON = new DowloadJSON(duongdan, attrs);
        dowloadJSON.execute();

        try {
            String chuoiJSON = dowloadJSON.get();
            JSONObject jsonObject = new JSONObject(chuoiJSON);
            String jsonKetQua = jsonObject.getString("ketqua");
            if (jsonKetQua.equals("true")) {
                kiemtra = true;
                String name = jsonObject.getString("name");
                upDataLogin(context, name);
            } else {
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return kiemtra;
    }
}
