package com.example.pccuong.appbook.model.login_registor;

import android.util.Log;

import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.example.pccuong.appbook.model.ObjectClass.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 2/26/2017.
 */

public class ModelRegistor {
    public Boolean DangKyThanhVien(Users users){
        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";
        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien");

        HashMap<String,String> hsName = new HashMap<>();
        hsName.put("name",users.getName());

        HashMap<String,String> hsAddres = new HashMap<>();
        hsAddres.put("email",users.getAddress());

        HashMap<String,String> hsenCrypted_password = new HashMap<>();
        hsenCrypted_password.put("encrypted_password",users.getEncrypted_password());

        HashMap<String,String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",users.getEmailDocQuyen());

        attrs.add(hsHam);
        attrs.add(hsName);
        attrs.add(hsAddres);
        attrs.add(hsenCrypted_password);
        attrs.add(hsEmailDocQuyen);

        DowloadJSON dowloadJSON = new DowloadJSON(duongdan,attrs);
        dowloadJSON.execute();

        try {
            String dulieuJSON = dowloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
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
