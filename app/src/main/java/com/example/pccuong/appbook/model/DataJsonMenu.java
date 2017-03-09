package com.example.pccuong.appbook.model;

import android.os.Bundle;
import android.util.Log;

import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.example.pccuong.appbook.model.ObjectClass.Categories;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class DataJsonMenu {


    String tennguoidung = "";

    public List<Categories> paraseJson(String datajson) {
        List<Categories> listCategories = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray categories = jsonObject.getJSONArray("categories");
            int count = categories.length();
            for (int i = 0; i < count; i++) {
                JSONObject values = categories.getJSONObject(i);
                Categories dataCategories = new Categories();
                dataCategories.setId(Integer.parseInt(values.getString("id")));
                dataCategories.setId_product(Integer.parseInt(values.getString("id_product")));
                dataCategories.setName(values.getString("name"));
                listCategories.add(dataCategories);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("kiemtrapaseJSON", listCategories.toString());
        return listCategories;

    }

    public List<Categories> getChildPositionMenu(int id) {
        List<Categories> listLoaiSanPham = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";
        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";

        HashMap<String,String>  hsHam = new HashMap<>();
        hsHam.put("ham","layListMenu");

        HashMap<String,String> shashMap = new HashMap<>();
        shashMap.put("id_product", String.valueOf(id));

        attrs.add(shashMap);
        attrs.add(hsHam);

        DowloadJSON dowloadJSON = new DowloadJSON(duongdan,attrs);
        dowloadJSON.execute();
        try {
            dataJSON = dowloadJSON.get();
            DataJsonMenu dataJsonMenu = new DataJsonMenu();
            listLoaiSanPham = dataJsonMenu.paraseJson(dataJSON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("kiemtrapaseJSON", listLoaiSanPham.toString());
        return listLoaiSanPham;


    }

    public String xuLyMenu(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    tennguoidung = object.getString("name");
                    Log.d("KiemtraTENNGUOIDUNG", tennguoidung);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "name");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

        return tennguoidung;
    }
}

