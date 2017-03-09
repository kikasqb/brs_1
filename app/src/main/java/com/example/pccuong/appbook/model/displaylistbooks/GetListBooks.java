package com.example.pccuong.appbook.model.displaylistbooks;

import android.util.Log;

import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.ObjectClass.NhaXuatBan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 3/2/2017.
 */

public class GetListBooks {
    public List<Books> GetProducModel(int id, String tenham,String tenmang, int limit){
        List<Books> bookses = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";
        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";
        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);
        HashMap<String, String> hsID = new HashMap<>();
        hsID.put("id", String.valueOf(id));
        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));
        attrs.add(hsHam);
        attrs.add(hsID);
        attrs.add(hsLimit);

        DowloadJSON dowloadJSON = new DowloadJSON(duongdan, attrs);
        dowloadJSON.execute();
        try {
            dataJSON = dowloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmang);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {

                JSONObject values = jsonArray.getJSONObject(i);
                Books books = new Books();
                books.setId(values.getInt("id"));
                books.setTitle(values.getString("title"));
                books.setImagebig(values.getString("imagebig"));
                books.setImagenho(values.getString("imagenho"));
                bookses.add(books);

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
