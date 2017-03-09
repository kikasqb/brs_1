package com.example.pccuong.appbook.model.chitietbooks;

import android.util.Log;

import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.example.pccuong.appbook.model.ObjectClass.Books;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 3/3/2017.
 */

public class ModelChiTietBooks {
    public Books ChiTietSanPhamBooks(String tenham, String tenmang, int id) {
        Books bookses = new Books();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";
        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";
        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);
        HashMap<String, String> hsId = new HashMap<>();
        hsId.put("id", String.valueOf(id));
        attrs.add(hsHam);
        attrs.add(hsId);
        DowloadJSON dowloadJSON = new DowloadJSON(duongdan, attrs);
        dowloadJSON.execute();
        try {
            dataJSON = dowloadJSON.get();
            Log.d("JSONCHITIET", dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmang);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject values = jsonArray.getJSONObject(i);
                bookses.setId(values.getInt("id"));
                bookses.setTitle(values.getString("title"));
                bookses.setImagenho(values.getString("imagenho"));
                bookses.setCover(values.getString("cover"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("kiemtrachuoimotmach", bookses.toString());
        return bookses;
    }
}
