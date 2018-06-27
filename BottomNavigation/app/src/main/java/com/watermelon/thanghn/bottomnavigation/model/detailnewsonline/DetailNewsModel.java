package com.watermelon.thanghn.bottomnavigation.model.detailnewsonline;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.model.app.PhotoLoader;
import com.watermelon.thanghn.bottomnavigation.model.app.database.DBNews;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline.IDetailNewsModel;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * Created by thang on 2/10/2018.
 */

public class DetailNewsModel {

    private DBNews dbNews;

    IDetailNewsModel callback;

    public DetailNewsModel(IDetailNewsModel callback) {
        this.callback = callback;
    }

    public void HandleGetHtmlDetailNews(String url) {
        RequestQueue queue = Volley.newRequestQueue(App.getAppContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String detail = "";
                org.jsoup.nodes.Document document = Jsoup.parse(response);

                Elements title = document.select("div.ovh.detail_w h1");
                Elements date = document.select("div.publishdate");
                Elements description = document.select("div.ovh.detail_w h2");
                document.select("table").remove();
                Elements main = document.select("div.ovh.content ");
                if (title.size()>0||date.size()>0||description.size()>0||main.size()>0){
                    detail += "<h2 style = \" color: red \">" + title.text() + "</h2>";
                    detail += "<font size=\" 1.2em \" style = \" color: #005500 \"><em>"
                            + date.text() + "</em></font>";

                    detail += "<p style = \" color: #99999 \"><b>" + "<font size=\" 4em \" >"
                            + description.text() + "</font></b></p>";
                    detail += "<font size=\" 4em \" >" + main.toString() + "</font>";
                    callback.onHandleGetHtmlDetailNewsSuccess(detail);
                }else {
                    callback.onHandleSaveImageInStorageFailed("Bài viết đang cập nhập");
                }

//                if (detail == null || detail.equals("")) {
//                    Toast.makeText(App.getAppContext(), "Kết nối yếu \n Vui lòng kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onHandleGetHtmlDetailNewsFailed("Vui lòng kiểm tra kết nối!");
            }
        });
        queue.add(stringRequest);
    }

    public void HandleSaveNewsInDevice(String linkNew, String titleNew, String pubDateNew, String imgNew, String contentNew) {
        dbNews = new DBNews(App.getAppContext());
        try {
            dbNews.insertdb(linkNew, titleNew, "", pubDateNew, contentNew, imgNew, "");
            callback.onHandleSaveNewsInDeviceSuccess("Lưu thành công");
        } catch (Exception ex) {
            callback.onHandleSaveNewsInDeviceFailed(String.valueOf(ex));
        }
    }

    public void HandleSaveImageInStorage(String linkImage, String imageName) {
        try {
           Picasso.with(App.getAppContext()).load(linkImage).into(new PhotoLoader(imageName));
            callback.onHandleSaveImageInStorageSuccess("Lưu ảnh thành công");
        } catch (Exception ex) {
            callback.onHandleSaveImageInStorageFailed(String.valueOf(ex));
        }
    }

}
