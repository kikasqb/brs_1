package com.example.pccuong.appbook.model.tunhien;

import android.util.Log;

import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.example.pccuong.appbook.model.DataJsonMenu;
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
 * Created by PCCuong on 2/11/2017.
 */

public class ModelTuNhien {
  public  List<Books> layListTop(String tenham,String tenmang){

      List<Books> bookses = new ArrayList<>();

      List<HashMap<String, String>> attrs = new ArrayList<>();
      String dataJSON = "";
      String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";
      HashMap<String, String> hsHam = new HashMap<>();
      hsHam.put("ham",tenham);
      attrs.add(hsHam);

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
              books.setImagebig(values.getString("anhsanpham"));
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

    public List<NhaXuatBan> layDanhSachNhaXuatBan(String tenham,String tenmang) {
        List<NhaXuatBan> nhaXuatBanList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";
        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";
        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);
        attrs.add(hsHam);

        DowloadJSON dowloadJSON = new DowloadJSON(duongdan, attrs);
        dowloadJSON.execute();
        try {
            dataJSON = dowloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmang);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {

                JSONObject values = jsonArray.getJSONObject(i);
                NhaXuatBan listNhaxuatBan = new NhaXuatBan();
                listNhaxuatBan.setId(values.getInt("id"));
                listNhaxuatBan.setTenSanPham(values.getString("tennhaxuatban"));
                listNhaxuatBan.setLinkHinh(values.getString("hinhanhnhaxuatban"));
                nhaXuatBanList.add(listNhaxuatBan);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("kiemtrachuoimotmach", nhaXuatBanList.toString());
        return nhaXuatBanList;
    }



}
